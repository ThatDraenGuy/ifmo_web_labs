class Quadrant {
    constructor(name, xSign, ySign) {
        this.xSign = xSign;
        this.ySign = ySign;
        this.drawFunc = getQuadrantByName(name);
        this.graphData = [];
    }
    draw() {
        this.graphData.length = 0;
        this.graphData.push(...this.drawFunc(this.xSign,this.ySign));
    }
    update(name) {
        this.drawFunc = getQuadrantByName(name);
    }
    getGraphData() {
        return this.graphData;
    }

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
        return new Quadrant("empty",x,y);
    }
    static get(x,y) {
        return Quadrant.quadrants[+(x>0)][+(y>0)];
    }
    static reDraw() {
        Quadrant.quadrants.forEach(element => {
            element.forEach(quadrant => {
                quadrant.draw();
            })
        })
    }
}

var shootingPoint = {x: null, y: null};

var max = 0;
var min = -max;

var chart  = new Chart("graph", {
    type: "scatter",
    data: {
        datasets: [{
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: Quadrant.get(-1,-1).getGraphData()
        },{
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: Quadrant.get(-1,1).getGraphData()
        },{
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: Quadrant.get(1,1).getGraphData()
        },
        {
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: Quadrant.get(1,-1).getGraphData()
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

generate(null,null,0);

function generate(x,y,r) {
    if (r!=max) {
        max = r;
        min = -max;
        Quadrant.reDraw();
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
function getQuadrantByName(name) {
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
function generateQuadrantFromFunc(func, signX) {
    return generateData(func,(signX>0 ? 0 : min), (signX>0 ? max : 0));
}


export function update(currentParams) {
    const x = currentParams.get('x');
    const y = currentParams.get('y');
    const r = currentParams.get('r');
    generate(x,y,r);
}

export function updateQuadrant(name, x, y) {
    Quadrant.get(x,y).update(name);
}