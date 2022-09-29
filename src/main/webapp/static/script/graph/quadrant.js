import SquareQuadrant from "./squareQuadrant.js";
import CircleQuadrant from "./circleQuadrant.js";
import TriangleQuadrant from "./triangleQuadrant.js";
import {maxX, maxY, minX, minY, startX, startY} from "../graph";

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
    static update(x,y,name) {
        Quadrant.quadrants[+(x>0)][+(y>0)] = Quadrant.getByName(x, y, name);
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
