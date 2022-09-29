import Quadrant from "./quadrant.js";
import {ctx, startY, startX} from "../graph.js";

export default class SquareQuadrant extends Quadrant {
    draw() {
        ctx.fillRect(startX,startY,this.xMulled(this.xMul)-startX, this.yMulled(this.yMul)-startY);
    }
}