import {FC, useEffect, useRef} from "react";
import {mapQuadrantsInfo, QuadrantsInfo, useQuadrantsQuery} from "../../services/quadrants";
import {Graph, GraphDrawer, GraphParams, GraphStyle} from "../../logic/GraphDrawer";
import {useAppSelector} from "../../hooks";
import * as styles from './graph.module.css';

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
            graphDrawer.draw(graph, graphStyle, mapQuadrantsInfo(quadrants))
        }
    }, [isSuccess, radius])

    return (
        <canvas ref={canvas} width={graphParams.width} height={graphParams.height} className={styles.layer2}/>
    )
}