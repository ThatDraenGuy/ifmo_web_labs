import { sendShootingReq } from "./connector.js";

const canvas = document.getElementById('graph');
const ctx = canvas.getContext('2d');

const MAIN_COLOR = 'rgba(0, 0, 200, 0.5)';
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
    constructor(xSign, ySign) {
        this.xSign = (xSign+1)/2;
        this.ySign = (1-ySign)/2;
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

var radius = null;
const point = new Point(0,0,60);
reDraw();

//redraws quadrants & axis (basically all of the graph besides shooting point)
export function reDraw() {
    //clear full canvas
    ctx.clearRect(0,0,width,height);
    ctx.fillStyle = MAIN_COLOR;
    //draw all quadrant graphs
    Quadrant.drawAll();
    drawAxis();
    updateLabels(radius);
}

function drawAxis() {
    ctx.strokeStyle = AXIS_COLOR;
    //draw OX
    ctx.beginPath();
    ctx.moveTo(0,startY);
    ctx.lineTo(width, startY);
    ctx.lineTo(width-10,startY-10);
    ctx.moveTo(width,startY);
    ctx.lineTo(width-10,startY+10);
    ctx.stroke();
    
    //draw OY
    ctx.beginPath();
    ctx.moveTo(startX,0);
    ctx.lineTo(startX-10,10);
    ctx.moveTo(startX,0);
    ctx.lineTo(startX+10,10);
    ctx.moveTo(startX,0);
    ctx.lineTo(startX, height);
    ctx.stroke();
}

function updatePoint(x,y) {
    reDraw();
    ctx.strokeStyle = POINT_COLOR;
    //draw crosshair
    ctx.beginPath();
    ctx.moveTo(x-7,y);
    ctx.lineTo(x+7,y);
    ctx.moveTo(x,y-7);
    ctx.lineTo(x,y+7);
    ctx.stroke();
    // set coords of point in bottom-right corner
    ctx.fillText(xFromCoord(x).toFixed(2)+" "+yFromCoord(y).toFixed(2), maxX,maxY);
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
function getXMinMax(x) {
    return x > 0 ? maxX : minX;
}
function getYMinMax(y) {
    return y >0 ? maxY : minY;
}

//convert absolute canvas coords to meaningful ones
function xFromCoord(xCoord) {
    return (xCoord-startX)*radius/(maxX-startX);
}
function yFromCoord(yCoord) {
    return (yCoord-startY)*radius/(startY-maxY);
}

class SquareQuadrant extends Quadrant {
    draw() {
        ctx.fillRect(startX,startY,getXMinMax(this.xSign)-startX, getYMinMax(this.ySign)-startY);
    }
}
class TriangleQuadrant extends Quadrant {
    draw() {
        ctx.beginPath();
        ctx.moveTo(startX,getYMinMax(this.ySign));
        ctx.lineTo(getXMinMax(this.xSign),startY);
        ctx.lineTo(startX,startY);
        ctx.fill();
    }
}
class CircleQuadrant extends Quadrant {
    draw() {
        let orientation = this.xSign==this.ySign;
        ctx.beginPath();
        ctx.moveTo(startX,startY);
        ctx.arc(startX,startY,Math.abs(getXMinMax(this.xSign)-startX),(2*this.ySign-1)*Math.PI/2,(1-this.xSign)*Math.PI, orientation);
        ctx.fill();
    }
}


export function update(currentParams) {
    const x = currentParams.get('x');
    const y = currentParams.get('y');
    const r = currentParams.get('r');
    radius = r;
    reDraw();
    let xCoord = startX + (maxX-startX)/radius*x;
    let yCoord = startY + (startY-maxY)/radius*y
    point.update(xCoord,yCoord);
}
export function updateQuadrant(name, x, y) {
    Quadrant.update(x,y,name);
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
    sendShootingReq(x,y,radius);    
}