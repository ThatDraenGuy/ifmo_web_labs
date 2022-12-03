import {QuadrantsInfo} from "../services/quadrants";

export interface GraphParams {
    startX: number,
    startY: number,
    maxX: number,
    maxY: number
    minX: number,
    minY: number,
    width: number,
    height: number,
}

export interface GraphStyle {
    background: string,
    figure: string,
    text: string,
    axis: string,
    point: string,
    hit: string,
    invalidHit: string,
    miss: string,
    invalidMiss: string
}

export const createGraphParams = (width: number, height: number, offset: number) => {
    const params: GraphParams = {startX: width/2, startY: height/2, minX: offset, maxY: offset, maxX: width - offset, minY: height - offset, width: width, height: height }
    return params;
}

export interface Graph extends GraphParams{
    canvas: CanvasRenderingContext2D,
    r: number
}

export interface GraphDrawer<T> {
    draw(graph: Graph, style: GraphStyle, data: T): void
}

export abstract class BaseGraphDrawer<T> implements GraphDrawer<T> {
    drawBackground(graph: Graph, style: GraphStyle): void {
        graph.canvas.clearRect(0,0,graph.width,graph.height);
        graph.canvas.fillStyle = style.background;
        graph.canvas.fillRect(0, 0, graph.width, graph.height);
    }
    drawAxis(graph: Graph, style: GraphStyle) {
        const ctx = graph.canvas;
        const startX = graph.startX
        const startY = graph.startY
        const width = graph.width
        const height = graph.height
        ctx.strokeStyle = style.axis
        //draw OX
        ctx.beginPath();
        ctx.moveTo(0,startY);
        ctx.lineTo(width, startY);
        ctx.lineTo(width-10,startY-5);
        ctx.moveTo(width,startY);
        ctx.lineTo(width-10,startY+5);
        ctx.stroke();

        //draw OY
        ctx.beginPath();
        ctx.moveTo(startX,0);
        ctx.lineTo(startX-5,10);
        ctx.moveTo(startX,0);
        ctx.lineTo(startX+5,10);
        ctx.moveTo(startX,0);
        ctx.lineTo(startX, height);
        ctx.stroke();
    }
    drawLabels(graph: Graph, style: GraphStyle) {
        const ctx = graph.canvas;
        const  r = graph.r;
        if (r==undefined) {
            //if no radius say that there is none
            ctx.fillStyle = style.axis;
            ctx.font = "24px serif";
            ctx.textAlign = 'center';
            ctx.textBaseline = 'bottom';
            ctx.fillText("Radius not set!", graph.startX, graph.minY+20);
            return;
        }
        const labels: Array<number|string> = [-r, -r/2, '', r/2, r];
        const offset = (r+"").length;
        ctx.fillStyle = style.text;
        ctx.font = "18px serif";
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        for (let i = 0; i < labels.length; i++) {
            const text = labels[i].toString();
            const factor = labels.length - 1;
            const xStep = graph.minX + (graph.maxX - graph.minX) / factor * i;
            const yStep = graph.maxY + (graph.minY - graph.maxY) / factor * (factor - i);

            //draw points on axis
            ctx.beginPath();
            ctx.arc(xStep, graph.startY, 3, 0, 2 * Math.PI);
            ctx.fill();
            ctx.beginPath();
            ctx.arc(graph.startX, yStep, 3, 0, 2 * Math.PI);
            ctx.fill();

            ctx.fillText(text, xStep, graph.startY + 20);
            ctx.fillText(text, graph.startX - 13 - (3 * offset), yStep);
        }
    }
    draw(graph: Graph, style: GraphStyle, data: T): void {
        this.drawBackground(graph, style);
        this.drawData(graph,style,data);
        this.drawAxis(graph, style);
        this.drawLabels(graph, style)
    }
    abstract drawData(graph: Graph, style: GraphStyle, data: T): void;
}

