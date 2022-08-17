export const graphVariants = new Map();
graphVariants.set("11", getEmptyQuadrant);
graphVariants.set("-11", getEmptyQuadrant);
graphVariants.set("-1-1", getEmptyQuadrant);
graphVariants.set("1-1", getEmptyQuadrant);

const quadrantsData = new Map();
quadrantsData.set("11", []);
quadrantsData.set("-11", []);
quadrantsData.set("-1-1", []);
quadrantsData.set("1-1", []);
var shootingPoint = {x: null, y: null};

var max = 0;
var min = -max;

const decoder = new Map();
decoder.set("11", {x:1,y:1});
decoder.set("-11", {x:-1,y:1});
decoder.set("-1-1", {x:-1,y:-1});
decoder.set("1-1", {x:1,y:-1});

var chart  = new Chart("graph", {
    type: "scatter",
    data: {
        datasets: [{
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: quadrantsData.get("-1-1")
        },{
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: quadrantsData.get("-11")
        },{
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: quadrantsData.get("11")
        },
        {
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: quadrantsData.get("1-1")
        },{
            type: "scatter",
            borderColor: "rgba(0,0,255,1)",
            pointRadius: 5,
            pointStyle: 'cross',
            data: [
                shootingPoint
            ]
        }]
    },
    options: {
        legend: {display: false},
        elements: {line: {showLine: true}}
    },
    });

generate(null,null);

function generate(x,y, r = 1) {
    if (r!=max) {
        // update max and min values and redraw the graph
        max = r;
        min = -max;
        quadrantsData.forEach((array, code) => {
            //clear data arrays
            array.length=0;
            //put new data in arrays
            array.push(...generateGraph(code));
        });
    }
    shootingPoint.x = x;
    shootingPoint.y = y;
    chart.update();
}
function generateData(func, i1, i2) {
    const step = (i2-i1)/8;
    var array = [];
    for (let x = i1; x <= i2; x += step) {
        const y = eval(func);
        array.push({x, y});
    };
    return array;
}

function generateQuadrantFromFunc(func, signX) {
    return generateData(func,(signX>0 ? 0 : min), (signX>0 ? max : 0));
}

function generateGraph(code) {
    //get this quadrant's function
    const quadrantFunction = graphVariants.get(code);
    //get signs of this quadrant
    const signX = decoder.get(code).x;
    const signY = decoder.get(code).y;
    //apply the function
    return quadrantFunction(signX, signY);
}

function getTriangleQuadrant(x, y) {
    let signX = Math.sign(x);
    let signY = Math.sign(y);
    return generateQuadrantFromFunc((signX==signY ? "-x+" : "x-") + "(" + signX + ")*max",x);
}

function getSquareQuadrant(x,y) {
    let func = (y>0 ? "max" : "min");
    return generateQuadrantFromFunc(func,x);
}

function getCircleQuadrant(x,y) {
    return generateQuadrantFromFunc((y>0 ? "" : "-") + "Math.sqrt(max**2-x**2)",x);
}

function getEmptyQuadrant(x,y) {
    return generateQuadrantFromFunc("null",x);
}

export function getQuadrantByName(name) {
    switch (name) {
        case "triangle":
            return getTriangleQuadrant;
        case "square":
            return getSquareQuadrant;
        case "circle":
            return getCircleQuadrant;
        default:
            return getEmptyQuadrant;
    }
}

export function update(currentParams) {
    const x = currentParams.get('x');
    const y = currentParams.get('y');
    const r = currentParams.get('r');
    generate(x,y,r);
}