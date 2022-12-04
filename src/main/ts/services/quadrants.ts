import {api} from "./api";
import {Graph} from "../logic/GraphDrawer";



export interface Quadrant {
    xMul: number,
    yMul: number,
    draw(graph: Graph): void
}

export class EmptyQuadrant implements Quadrant {
    xMul: number;
    yMul: number;
    constructor(xMul:number,yMul:number) {
        this.xMul=xMul;
        this.yMul=yMul;
    }
    draw(graph: Graph): void {}

    xMulled(graph: Graph): number {
        return this.xMul*(this.xMul >=0 ? (graph.maxX-graph.startX) : (graph.startX-graph.minX))
    }
    yMulled(graph: Graph): number {
        return this.yMul*(this.yMul >=0 ? (graph.maxY - graph.startY) : (graph.startY - graph.minY))
    }
}

export class SquareQuadrant extends EmptyQuadrant{
    draw(graph: Graph): void {
        graph.canvas.fillRect(graph.startX, graph.startY, this.xMulled(graph), this.yMulled(graph));
    }
}

export class CircleQuadrant extends EmptyQuadrant {
    draw(graph: Graph) {
        graph.canvas.beginPath();
        graph.canvas.moveTo(graph.startX, graph.startY);
        const orientation: boolean = (this.xMul >=0) != (this.yMul>=0);
        graph.canvas.arc(graph.startX, graph.startY, Math.abs(this.xMulled(graph)), this.yPiMul()*Math.PI/2, this.xPiMul()*Math.PI, orientation)
        graph.canvas.fill();
    }
    xPiMul() {
        return this.xMul >=0 ? 0 : 1;
    }
    yPiMul() {
        return this.yMul >=0 ? -1 : 1
    }
}

export class TriangleQuadrant extends EmptyQuadrant {
    draw(graph: Graph) {
        graph.canvas.beginPath();
        graph.canvas.moveTo(graph.startX, graph.startY + this.yMulled(graph));
        graph.canvas.lineTo(graph.startX + this.xMulled(graph), graph.startY);
        graph.canvas.lineTo(graph.startX, graph.startY);
        graph.canvas.fill();
    }
}

interface QuadrantDto {
    type: string,
    xMul: number,
    yMul: number
}

export interface QuadrantsInfoDto {
    quadrants: Array<QuadrantDto>
}

export interface QuadrantsInfo {
    quadrants: Array<Quadrant>
}

export const mapQuadrantsInfo = (quadrantsInfoDto: QuadrantsInfoDto): QuadrantsInfo => {
    const mapQuadrant = (dto: QuadrantDto): Quadrant => {
        switch (dto.type) {
            case "square": return new SquareQuadrant(dto.xMul,dto.yMul);
            case "circle": return new CircleQuadrant(dto.xMul,dto.yMul);
            case "triangle": return new TriangleQuadrant(dto.xMul,dto.yMul);
            default: return new EmptyQuadrant(dto.xMul, dto.yMul)
        }
    }
    const quadrantsInfo: QuadrantsInfo = {quadrants: []};
    for (const quadrantDto of quadrantsInfoDto.quadrants) {
        quadrantsInfo.quadrants.push(mapQuadrant(quadrantDto))
    }
    return quadrantsInfo;
}
export const quadrantsApi = api.injectEndpoints({
    endpoints: build => ({
        quadrants: build.query<QuadrantsInfoDto, void>({
            query: () => ({
                url: "/quadrants",
            })
        })
    })
})

export const {useQuadrantsQuery} = quadrantsApi