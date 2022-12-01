import React, {FC, useEffect, useRef} from "react";
import {useAppSelector} from "../../hooks";
import {Graph, GraphParams, GraphStyle} from "../../logic/GraphDrawer";
import {useShootMutation} from "../../services/attempts";

interface CLickPosition {
    x: number,
    y: number
}

class ClickGraphDrawer {
    clear(graph: Graph): void {
        graph.canvas.clearRect(0,0,graph.width,graph.height)
    }
    draw(graph: Graph, style: GraphStyle, data: CLickPosition): void {
        const ctx = graph.canvas;
        const x = data.x;
        const y = data.y;
        this.clear(graph);
        ctx.strokeStyle = style.point;
        //draw crosshair
        ctx.beginPath();
        ctx.moveTo(x-7,y);
        ctx.lineTo(x+7,y);
        ctx.moveTo(x,y-7);
        ctx.lineTo(x,y+7);
        ctx.stroke();
    }
}

export interface ClickableGraphProps {
    graphParams: GraphParams,
    graphStyle: GraphStyle
}

export const ClickableGraph: FC<ClickableGraphProps> = ({graphParams, graphStyle}) => {
    const drawer = new ClickGraphDrawer();
    const isRadiusInvalid = (radius: number) => radius==undefined || radius==0;
    const radiusCheck = (oldValue: number, newValue: number) => {
        return isRadiusInvalid(oldValue) == isRadiusInvalid(newValue);
    }
    const getOffsets = (e: React.MouseEvent<HTMLCanvasElement>) => {
        return {
            x: e.clientX - canvas.current.getBoundingClientRect().left,
            y: e.clientY - canvas.current.getBoundingClientRect().top
        }
    }
    const mapCoords = (graph: Graph, x: number, y: number) => {
        return {
            x: Math.round((x - graph.startX)*graph.r/(graph.maxX - graph.startX) * 100) / 100,
            y: Math.round((y - graph.startY)*graph.r/(graph.maxY - graph.startY) * 100) / 100
        }
    }
    const radius = useAppSelector(state => state.chooser.r, radiusCheck)
    const [shootPost, {}] = useShootMutation();

    const canvas = useRef<HTMLCanvasElement>();

    const graph: Graph = canvas.current==undefined ? undefined : {canvas: canvas.current.getContext('2d'), ...graphParams, r: radius};

    const onMouseMove = (e: React.MouseEvent<HTMLCanvasElement>) => {
        if (! isRadiusInvalid(radius)) {
            const {x, y} = getOffsets(e);
            drawer.draw(graph, graphStyle, {x: x, y: y})
        }
    }
    const onMouseLeave = (e: React.MouseEvent<HTMLCanvasElement>) => {
        if (! isRadiusInvalid(radius)) {
            drawer.clear(graph);
        }
    }
    const onMouseClick = (e: React.MouseEvent<HTMLCanvasElement>) => {
        if (! isRadiusInvalid(radius)) {
            const {x, y} = getOffsets(e);
            const {x: xMapped, y: yMapped} = mapCoords(graph, x, y);
            shootPost({x: xMapped, y: yMapped, r: radius});
        }
    }

    return (
        <canvas width={graphParams.width} height={graphParams.height} ref={canvas} onMouseMove={onMouseMove} onMouseLeave={onMouseLeave} onClick={onMouseClick}/>
    )
}