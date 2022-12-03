import {FC, useEffect, useRef} from "react";
import {mapQuadrantsInfo, QuadrantsInfo, useQuadrantsQuery} from "../../services/quadrants";
import {Graph, GraphDrawer, GraphParams, GraphStyle} from "../../logic/GraphDrawer";
import {useAppSelector} from "../../hooks";
import * as styles from './graph.module.css';
import {useAttemptsPageQuery, UserAttempt} from "../../services/attempts";

export interface GraphHolderParams {
    graphDrawer: GraphDrawer<QuadrantsInfo>,
    pointsDrawer: GraphDrawer<Array<UserAttempt>>
    graphParams: GraphParams,
    graphStyle: GraphStyle
}

export const GraphHolder: FC<GraphHolderParams> = ({graphDrawer, pointsDrawer, graphParams, graphStyle}) => {
    const {data: quadrants, isSuccess: isQuadrantsLoadingSuccess} = useQuadrantsQuery();
    const radius = useAppSelector(state => state.chooser.r);
    const currentPage = useAppSelector(state => state.pagination.currentPage);
    const itemsPerPage = useAppSelector(state => state.pagination.itemsPerPage);
    const {data: attemptsPage, isSuccess: isPointsLoadingSuccess} = useAttemptsPageQuery({page: currentPage-1, size: itemsPerPage});
    const canvas = useRef<HTMLCanvasElement>();

    useEffect(() => {
        if (isQuadrantsLoadingSuccess && isPointsLoadingSuccess) {
            const graph: Graph = {canvas: canvas.current.getContext("2d"), ...graphParams, r: radius}
            graphDrawer.draw(graph, graphStyle, mapQuadrantsInfo(quadrants));
            pointsDrawer.draw(graph, graphStyle, attemptsPage.attempts);
        }
    }, [isQuadrantsLoadingSuccess, isPointsLoadingSuccess, radius, attemptsPage])

    return (
        <canvas ref={canvas} width={graphParams.width} height={graphParams.height} className={styles.layer2}/>
    )
}