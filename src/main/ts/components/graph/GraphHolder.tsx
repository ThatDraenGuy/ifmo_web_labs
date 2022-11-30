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
    const chooser = useAppSelector(state => state.chooser)
    const canvas = useRef<HTMLCanvasElement>();

    useEffect(() => {
        if (isSuccess) {
            const graph: Graph = {canvas: canvas.current.getContext("2d"), ...graphParams, r: chooser.r}
            graphDrawer.draw(graph, graphStyle, quadrants)
        }
    }, [isSuccess])

    return (
        <div>
            <canvas ref={canvas} width={graphParams.width} height={graphParams.height}/>
        </div>)
}