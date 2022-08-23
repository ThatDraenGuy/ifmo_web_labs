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

const canvas = document.getElementById('graph');
const ctx = canvas.getContext('2d');

const width = canvas.width;
const height = canvas.height;
const margin = width/8;
const minX = margin;
const minY = margin;
const maxX = width-margin;
const maxY = height-margin;
const startX = (maxX+minX)/2;
const startY = (maxY+minY)/2;

var radius = null;
reDraw();

export function reDraw() {
    ctx.clearRect(0,0,width,height);
    ctx.fillStyle = 'rgba(0, 0, 200, 0.5)';
    Quadrant.drawAll();
    drawAxis();
    updateLabels(radius);
    ctx.save();
}

function drawAxis() {
    ctx.strokeStyle = '#000';
    ctx.beginPath();
    ctx.moveTo(0,startY);
    ctx.lineTo(width, startY);
    ctx.lineTo(width-10,startY-10);
    ctx.moveTo(width,startY);
    ctx.lineTo(width-10,startY+10);
    ctx.stroke();
    
    ctx.beginPath();
    ctx.moveTo(startX,0);
    ctx.lineTo(startX-10,10);
    ctx.moveTo(startX,0);
    ctx.lineTo(startX+10,10);
    ctx.moveTo(startX,0);
    ctx.lineTo(startX, height);
    ctx.stroke();

    ctx.fillStyle = 'rgba(0, 0, 200, 0.5)';
}

function updatePoint(x,y) {
    ctx.restore();
    ctx.save();
    ctx.strokeStyle = 'rgba(200, 0, 0, 1)';
    ctx.beginPath();
    ctx.moveTo(x-7,y);
    ctx.lineTo(x+7,y);
    ctx.moveTo(x,y-7);
    ctx.lineTo(x,y+7);
    ctx.stroke();
}
function updateLabels(r) {
    if (r===null || r===undefined) return;
    const labels = [-r, -r/2, '', r/2, r];
    ctx.fillStyle = '#000';
    ctx.font = "18px serif";
    ctx.textAlign = 'center';
    ctx.textBaseLine = 'bottom';
    for (let i = 0; i < labels.length; i++) {
        const text = labels[i];
        const factor = labels.length-1;
        const xStep = minX+(maxX-minX)/factor*i;
        const yStep = minY+(maxY-minY)/factor*(factor-i);

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



function getXMinMax(x) {
    return x > 0 ? maxX : minX;
}
function getYMinMax(y) {
    return y >0 ? maxY : minY;
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
        ctx.arc(startX,startY,getXMinMax(this.xSign)-startX,(2*this.ySign-1)*Math.PI/2,(1-this.xSign)*Math.PI, orientation);
        ctx.fill();
    }
}


export function update(currentParams) {
    const x = currentParams.get('x');
    const y = currentParams.get('y');
    const r = currentParams.get('r');
    radius = r;
    let xCoord = startX + (maxX-startX)/radius*x;
    let yCoord = startY + (startY-maxY)/radius*y
    updatePoint(xCoord,yCoord);
}
export function updateQuadrant(name, x, y) {
    Quadrant.update(x,y,name);
}

canvas.onmousemove = (event) => {
    updatePoint(event.offsetX, event.offsetY);
}