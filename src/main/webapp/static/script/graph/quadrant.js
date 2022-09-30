// import SquareQuadrant from "./squareQuadrant.js";
// import CircleQuadrant from "./circleQuadrant.js";
// import TriangleQuadrant from "./triangleQuadrant.js";
import {maxX, maxY, minX, minY, startX, startY} from "../graph.js";

export default class Quadrant {
    constructor(xMul, yMul) {
        this.xMul = xMul;
        this.yMul = yMul;
    }
    draw() {}

    // needed to get min or max depending on quadrant I'm in
    xMulled(xMul) {
        if (xMul>=0) return startX + (maxX-startX)*xMul;
        return startX + xMul*(startX-minX);
    }
    yMulled(yMul) {
        if (yMul>=0) return startY + (startY-maxY)*yMul;
        return startY + yMul*(minY-startY);
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
        return new Quadrant(x,y);
    }
    static get(x,y) {
        return Quadrant.quadrants[+(x>0)][+(y>0)];
    }
    static update(quadrant) {
        let x = quadrant.xMul > 0 ? 1 : 0;
        let y = quadrant.yMul > 0 ? 1 : 0;
        Quadrant.quadrants[x][y] = quadrant;
    }

    static drawAll() {
        Quadrant.quadrants.forEach(element => {
            element.forEach(quadrant => {
                quadrant.draw();
            })
        })
    }
}
