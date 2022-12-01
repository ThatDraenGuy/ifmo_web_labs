import {FC, useEffect, useRef} from "react";
import {QuadrantsInfo, useQuadrantsQuery} from "../../services/quadrants";
import {Graph, GraphDrawer, GraphParams, GraphStyle} from "../../logic/GraphDrawer";
import {useAppSelector} from "../../hooks";

export interface GraphHolderParams {
    graphDrawer: GraphDrawer<QuadrantsInfo>,
    graphParams: GraphParams,
    graphStyle: GraphStyle
}

export const GraphHolder: FC<GraphHolderParams> = ({graphDrawer, graphParams, graphStyle}) => {
    const {data: quadrants, isFetching, isSuccess} = useQuadrantsQuery();
    const radius = useAppSelector(state => state.chooser.r)
    const canvas = useRef<HTMLCanvasElement>();

    useEffect(() => {
        if (isSuccess) {
            const graph: Graph = {canvas: canvas.current.getContext("2d"), ...graphParams, r: radius}
            graphDrawer.draw(graph, graphStyle, quadrants)
        }
    }, [isSuccess, radius])

    return (
        <div>
            <canvas ref={canvas} width={graphParams.width} height={graphParams.height}/>
        </div>)
}