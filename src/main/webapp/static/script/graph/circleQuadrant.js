import Quadrant from "./quadrant.js";
import {ctx, startX, startY} from "../graph.js";

export default class CircleQuadrant extends Quadrant {
    xSign() {
        return this.xMul >=0 ? 0 : 1;
    }
    ySign() {
        return this.yMul >= 0 ? -1 : 1;
    }
    draw() {
        let orientation = (this.xMul >= 0)!==(this.yMul >= 0);
        ctx.beginPath();
        ctx.moveTo(startX,startY);
        ctx.arc(startX,startY,Math.abs(this.xMulled(this.xMul)-startX),this.ySign()*Math.PI/2,this.xSign()*Math.PI, orientation);
        ctx.fill();
    }
}