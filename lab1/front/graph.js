

generate(1);

function generate(x) {
const max = x;
const min = -max;
const graphVariants = new Map();
graphVariants.set("11", getCircleQuadrant);
graphVariants.set("-11", getSquareQuadrant);
graphVariants.set("-1-1", getTriangleQuadrant);
graphVariants.set("1-1", getEmptyQuadrant);
new Chart("graph", {
  type: "line",
  data: {
    labels: generateData("x",min,max),
    datasets: [{
      fill: true,
      pointRadius: 1,
      borderColor: "rgba(255,0,0,0.5)",
      data: generateGraph(-1,-1)
    },{
        fill: true,
        pointRadius: 1,
        borderColor: "rgba(255,0,0,0.5)",
        data: generateGraph(-1,1)
    },{
        fill: true,
        pointRadius: 1,
        borderColor: "rgba(255,0,0,0.5)",
        data: generateGraph(1, 1)
    },
    {
        fill: true,
        pointRadius: 1,
        borderColor: "rgba(255,0,0,0.5)",
        data: generateGraph(1, -1)
    }]
  },
  options: {
    legend: {display: false}
  }
});

function generateData(value, i1, i2) {
    const step = max/8;
    var array = [];
    for (let i = min; i<i1; i+= step) {
        array.push(null);
    }
    for (let x = i1; x <= i2; x += step) {
      array.push(eval(value));
    };
    for (let i = i2; i<max; i+= step) {
        array.push(null);
    }
    return array;
}

function generateGraph(signX, signY) {
    const quadrantFunction = graphVariants.get(""+signX+signY);
    const func = quadrantFunction(signX, signY);
    return generateData(func, (signX>0 ? 0 : min), (signX>0 ? max : 0));
}

function getTriangleQuadrant(x, y) {
    let signX = Math.sign(x);
    let signY = Math.sign(y);
    return (signX==signY ? "-x+" : "x-") + "(" + signX + ")*max";
}

function getSquareQuadrant(x,y) {
    return (y>0 ? "max" : "min");
}

function getCircleQuadrant(x,y) {
    return (y>0 ? "Math.sqrt(max**2-x**2)" : "-Math.sqrt(max**2-x**2)")
}

function getEmptyQuadrant(x,y) {
    return "null";
}

}

function update() {
}