import { sendShootingReq } from "./utils.js";

const canvas = document.getElementById('graph');
const ctx = canvas.getContext('2d');

const GRAPH_COLOR = 'rgba(0, 0, 200, 0.5)';
const BACK_COLOR = 'rgba(150,150,150,0)';
const AXIS_COLOR = '#000';
const TEXT_COLOR = '#000';
const POINT_COLOR = 'rgba(200, 0, 0, 1)'

const width = canvas.width;
const height = canvas.height;
const margin = width/8; // margin between the actual graph and borders of canvas
//min, max and start coordinates calculated
const minX = margin;
const minY = margin;
const maxX = width-margin;
const maxY = height-margin;
const startX = (maxX+minX)/2;
const startY = (maxY+minY)/2;

// parent class for all quadrant types + a point of referencing all of them
class Quadrant {
    constructor(xMul, yMul) {
        this.xMul = xMul;
        this.yMul = yMul;
    }
    draw() {}

    static quadrants = [
        [
            Quadrant.default(-1,-1),
            Quadrant.default(-1,1)
        ],
        [
            Quadrant.default(1,-1),
            Quadrant.default(1,1)
        ]
    ]
    static default(x,y) {
        return new Quadrant(x,y);
    }
    static get(x,y) {
        return Quadrant.quadrants[+(x>0)][+(y>0)];
    }
    static update(x,y,name) {
        let quadrant = Quadrant.getByName(x,y,name);
        Quadrant.quadrants[+(x>0)][+(y>0)] = quadrant;
    }
    static getByName(x,y,name) {
        switch (name) {
            case "triangle":
                return new TriangleQuadrant(x,y);
            case "square":
                return new SquareQuadrant(x,y);
            case "circle":
                return new CircleQuadrant(x,y);
            default:
                return new Quadrant(x,y);
        }
    }
    static drawAll() {
        Quadrant.quadrants.forEach(element => {
            element.forEach(quadrant => {
                quadrant.draw();
            })
        })
    }
}

// class for a point - handles its position & movement (animations included)
class Point {
    constructor(x,y, time) {
        this.x = x;
        this.y = y;
        this.targetX = x;
        this.targetY = y;
        this.time = time;
        this.counter = 0;
    }
    update(x,y) {
        if (isNaN(x) || isNaN(y)) return;
        window.cancelAnimationFrame(this.req);
        this.targetX = x;
        this.targetY = y;
        this.stepX = (this.targetX-this.x)/(this.time);
        this.stepY = (this.targetY-this.y)/(this.time);
        this.counter = 0;
        this.req = requestAnimationFrame(frame)
    }
    set(x,y) {
        window.cancelAnimationFrame(this.req);
        this.x = x;
        this.y = y;
        this.targetX = x;
        this.targetY = y;
        updatePoint(this.x,this.y);
    }
    frame() {
        this.x = this.x + this.stepX;
        this.y = this.y + this.stepY;
        updatePoint(this.x,this.y);
        this.counter++;
        if (this.counter >= this.time) {
            this.set(this.targetX, this.targetY);
            return;
        }
        this.req = requestAnimationFrame(frame)
    }
}
function frame() {
    point.frame();
}

let radius = null;
const point = new Point(0,0,60);
const oldPoints = [];
reDraw();

//redraws quadrants & axis (basically all of the graph besides shooting point)
export function reDraw() {
    //clear full canvas
    ctx.clearRect(0,0,width,height);
    ctx.fillStyle = BACK_COLOR;
    ctx.fillRect(0,0,width,height);
    ctx.fillStyle = GRAPH_COLOR;
    //draw all quadrant graphs
    Quadrant.drawAll();
    drawAxis();
    updateLabels(radius);
    oldPoints.forEach(point => {
        drawPoint(coordFromX(point.x),coordFromY(point.y),point.style);
    })
}

function drawAxis() {
    ctx.strokeStyle = AXIS_COLOR;
    //draw OX
    ctx.beginPath();
    ctx.moveTo(0,startY);
    ctx.lineTo(width, startY);
    ctx.lineTo(width-10,startY-5);
    ctx.moveTo(width,startY);
    ctx.lineTo(width-10,startY+5);
    ctx.stroke();
    
    //draw OY
    ctx.beginPath();
    ctx.moveTo(startX,0);
    ctx.lineTo(startX-5,10);
    ctx.moveTo(startX,0);
    ctx.lineTo(startX+5,10);
    ctx.moveTo(startX,0);
    ctx.lineTo(startX, height);
    ctx.stroke();
}

function updatePoint(x,y) {
    reDraw();
    ctx.strokeStyle = POINT_COLOR;
    //draw crosshair
    drawPoint(x,y);
    // set coords of point in bottom-right corner
    ctx.fillText(xFromCoord(x).toFixed(2)+" "+yFromCoord(y).toFixed(2), maxX-10,maxY);
}
function drawPoint(x,y,style = POINT_COLOR) {
    ctx.strokeStyle = style;
    //draw crosshair
    ctx.beginPath();
    ctx.moveTo(x-7,y);
    ctx.lineTo(x+7,y);
    ctx.moveTo(x,y-7);
    ctx.lineTo(x,y+7);
    ctx.stroke();
}
function updateLabels(r) {
    if (r===null || r===undefined) {
        //if no radius say that there is none
        ctx.fillStyle = POINT_COLOR;
        ctx.font = "24px serif";
        ctx.textAlign = 'center';
        ctx.textBaseLine = 'bottom';
        ctx.fillText("Radius not set!", startX, maxY+20);
        return;
    }
    const labels = [-r, -r/2, '', r/2, r];
    ctx.fillStyle = TEXT_COLOR;
    ctx.font = "18px serif";
    ctx.textAlign = 'center';
    ctx.textBaseLine = 'bottom';
    for (let i = 0; i < labels.length; i++) {
        const text = labels[i];
        const factor = labels.length-1;
        const xStep = minX+(maxX-minX)/factor*i;
        const yStep = minY+(maxY-minY)/factor*(factor-i);
        
        //draw points on axis
        ctx.beginPath();
        ctx.arc(xStep,startY,3,0,2*Math.PI);
        ctx.fill();
        ctx.beginPath();
        ctx.arc(startX,yStep,3,0,2*Math.PI);
        ctx.fill();

        ctx.fillText(text, xStep,startY+20);
        ctx.fillText(text, startX-20, yStep);
    }
}


// needed to get min or max depending on quadrant I'm in
function xMulled(xMul) {
    if (xMul>=0) return startX + (maxX-startX)*xMul;
    return startX + xMul*(startX-minX);
}
function yMulled(yMul) {
    if (yMul>=0) return startY + (startY-maxY)*yMul;
    return startY + yMul*(minY-startY);
}

//convert absolute canvas coords to meaningful ones
function xFromCoord(xCoord) {
    return (xCoord-startX)*radius/(maxX-startX);
}
function yFromCoord(yCoord) {
    return (yCoord-startY)*radius/(startY-maxY);
}
//do the revert conversion
function coordFromX(x) {
    return startX + (maxX-startX)/radius*x;
}
function coordFromY(y) {
    return startY + (startY-maxY)/radius*y;
}

class SquareQuadrant extends Quadrant {
    draw() {
        ctx.fillRect(startX,startY,xMulled(this.xMul)-startX, yMulled(this.yMul)-startY);
    }
}
class TriangleQuadrant extends Quadrant {
    draw() {
        ctx.beginPath();
        ctx.moveTo(startX,yMulled(this.yMul));
        ctx.lineTo(xMulled(this.xMul),startY);
        ctx.lineTo(startX,startY);
        ctx.fill();
    }
}
class CircleQuadrant extends Quadrant {
    xSign() {
        return this.xMul >=0 ? 0 : 1;
    }
    ySign() {
        return this.yMul >= 0 ? -1 : 1;
    }
    draw() {
        console.log(this.xMul, this.yMul, this.ySign()*Math.PI/2, this.xSign()*Math.PI);
        let orientation = (this.xMul >= 0)!==(this.yMul >= 0);
        ctx.beginPath();
        ctx.moveTo(startX,startY);
        ctx.arc(startX,startY,Math.abs(xMulled(this.xMul)-startX),this.ySign()*Math.PI/2,this.xSign()*Math.PI, orientation);
        ctx.fill();
    }
}


export function updateGraph(currentParams, shouldShoot = false) {
    const x = currentParams.get('x');
    const y = currentParams.get('y');
    const r = currentParams.get('r');
    radius = r;
    reDraw();
    let xCoord = coordFromX(x);
    let yCoord = coordFromY(y);
    point.update(xCoord,yCoord);
    if (shouldShoot) {
        shoot(x,y);
        sendShootingReq(x,y,r);        
    } 
}
export function updateQuadrant(name, x, y) {
    Quadrant.update(x,y,name);
}
export function setOldPoints(points) {
    oldPoints.length = 0;
    points.forEach(point => {
        if ('x' in point && 'y' in point) {
            oldPoints.push({x:point.x,y:point.y,style:oldPointStyle()});
        }
    });
    reDraw();
}
function shoot(x,y) {
    oldPoints.push({x:x,y:y,style:oldPointStyle()});
}
function oldPointStyle() {
    return "rgba("+(Math.random()*100+100)+", "+(200+Math.random()*155)+", "+(Math.random()*100+100)+",1)";
}
//mouse stuff
canvas.onmousemove = (event) => {
    if (radius===null || radius===undefined) return;
    point.set(event.offsetX, event.offsetY);
}

canvas.onmousedown = (event) => {
    if (radius===null || radius===undefined) return;
    const xCoord = event.offsetX;
    const yCoord = event.offsetY;
    let x = xFromCoord(xCoord).toFixed(2);
    let y = yFromCoord(yCoord).toFixed(2);
    shoot(x,y);
    sendShootingReq(x,y,radius);    
}

