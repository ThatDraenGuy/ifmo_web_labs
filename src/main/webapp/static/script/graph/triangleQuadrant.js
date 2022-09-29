import Quadrant from "./quadrant.js";
import {ctx, startY, startX} from "../graph.js";

export default class TriangleQuadrant extends Quadrant {
    draw() {
        ctx.beginPath();
        ctx.moveTo(startX,this.yMulled(this.yMul));
        ctx.lineTo(this.xMulled(this.xMul),startY);
        ctx.lineTo(startX,startY);
        ctx.fill();
    }
}