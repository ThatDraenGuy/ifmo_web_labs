const graphVariants = new Map();
graphVariants.set("11", getCircleQuadrant);
graphVariants.set("-11", getSquareQuadrant);
graphVariants.set("-1-1", getTriangleQuadrant);
graphVariants.set("1-1", getEmptyQuadrant);
var chart = new Chart("graph");
var context = $("#graph");

generate(null,null,1);
function generate(x,y, r = 1) {
    const max = r;
    const min = -max;
    const labels = generateLabels(x,r);
    chart.destroy();
    chart  = new Chart(context, {
    type: "scatter",
    data: {
        labels: labels,
        datasets: [{
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: generateGraph(-1,-1)
        },{
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: generateGraph(-1,1)
        },{
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: generateGraph(1, 1)
        },
        {
            fill: true,
            pointRadius: 1,
            borderColor: "rgba(255,0,0,0.5)",
            showLine: true,
            data: generateGraph(1, -1)
        },{
            type: "scatter",
            borderColor: "rgba(0,0,255,1)",
            pointRadius: 5,
            pointStyle: 'cross',
            data: [{
                x: x,
                y: y
            }]
        }]
    },
    options: {
        legend: {display: false},
        elements: {line: {showLine: true}}
    },
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

    function generateLine(value, i1, i2) {
        var array = [];
        labels.forEach(x => {
            if (x < i1 || x > i2) {
            }  else {
                const y = eval(value);
                array.push({x, y});
            }
        });
        return array;
    }

    function generateGraph(signX, signY) {
        const quadrantFunction = graphVariants.get(""+signX+signY);
        const func = quadrantFunction(signX, signY);
        return generateLine(func, (signX>0 ? 0 : min), (signX>0 ? max : 0));
    }
    function generateLabels(x,r) {
        var array = generateData("x",min,max);
        // if (x!=null && !array.includes(x)) {
        //     array.push(x);
        //     array.sort((a,b) => a-b);
        // }
        return array;
    }
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

export function update(currentParams) {
    const x = currentParams.get('x');
    const y = currentParams.get('y');
    const r = currentParams.get('r');
    generate(x,y,r);
}