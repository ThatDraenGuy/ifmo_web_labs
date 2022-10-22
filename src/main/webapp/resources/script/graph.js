let canvas;
let ctx;

const POINT_COLOR = 'rgba(200, 0, 0, 1)'


function setCanvas(element, r) {
    canvas = element;
    ctx = element.getContext('2d')
    if (r!==undefined) {
        element.addEventListener("mousemove", onMouseMove);
        element.addEventListener("click", onCLick);
        element.addEventListener("mouseleave", clear)
    }
}

function onCLick(event) {
    send([{name: "x",value: (event.offsetX/canvas.width*2 - 1).toFixed(2)}, {name: "y", value: (-1 * event.offsetY/canvas.height*2 + 1).toFixed(2)}]);
}

function onMouseMove(event) {
    clear();
    drawPoint(event.offsetX, event.offsetY);
}


function clear() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
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