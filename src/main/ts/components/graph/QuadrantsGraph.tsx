import {FC} from "react";
import {GraphHolder} from "./GraphHolder";
import {
    BaseGraphDrawer,
    createGraphParams,
    Graph,
    GraphDrawer,
    GraphStyle,
} from "../../logic/GraphDrawer";
import {QuadrantsInfo} from "../../services/quadrants";
import {
    GRAPH_AXIS_COLOR,
    GRAPH_BACK_COLOR,
    GRAPH_COLOR,
    GRAPH_HEIGHT, GRAPH_HIT_COLOR, GRAPH_INVALID_HIT_COLOR, GRAPH_INVALID_MISS_COLOR, GRAPH_MISS_COLOR,
    GRAPH_OFFSET, GRAPH_POINT_COLOR,
    GRAPH_TEXT_COLOR,
    GRAPH_WIDTH
} from "../../constants/constants";
import {ClickableGraph} from "./ClickableGraph";
import * as styles from './graph.module.css';
import {UserAttempt} from "../../services/attempts";


export class QuadrantsDrawer extends BaseGraphDrawer<QuadrantsInfo> {
    drawData(graph: Graph, style: GraphStyle, data: QuadrantsInfo): void {
        graph.canvas.fillStyle = style.figure;
        for (const quadrant of data.quadrants) {
            quadrant.draw(graph);
        }
    }
}

export class PointsDrawer implements GraphDrawer<Array<UserAttempt>> {
    mapCoords(graph: Graph, x: number, y: number) {
        return {
            x: x / graph.r * (graph.maxX - graph.startX) + graph.startX,
            y: y / graph.r * (graph.maxY - graph.startY) + graph.startY
        }
    }
    draw(graph: Graph, style: GraphStyle, data: Array<UserAttempt>) {
        const ctx = graph.canvas;
        for (const userAttempt of data) {
            const attempt = userAttempt.attempt;
            const {x,y} = this.mapCoords(graph, attempt.coords.x, attempt.coords.y);
            const isValid = attempt.coords.r == graph.r;
            ctx.fillStyle = attempt.shot.res ? (isValid ? style.hit : style.invalidHit) : (isValid ? style.miss : style.invalidMiss);
            ctx.beginPath();
            ctx.arc(x,y,6,0,2*Math.PI);
            ctx.fill();
        }
    }
}


export const QuadrantsGraph: FC<any> = () => {
    const graphDrawer: GraphDrawer<QuadrantsInfo> = new QuadrantsDrawer();
    const pointsDrawer: GraphDrawer<Array<UserAttempt>> = new PointsDrawer();
    const graphParams = createGraphParams(GRAPH_WIDTH, GRAPH_HEIGHT, GRAPH_OFFSET);
    const graphStyle: GraphStyle = {
        background: GRAPH_BACK_COLOR, figure: GRAPH_COLOR, text: GRAPH_TEXT_COLOR, axis: GRAPH_AXIS_COLOR, point: GRAPH_POINT_COLOR,
        hit: GRAPH_HIT_COLOR, invalidHit: GRAPH_INVALID_HIT_COLOR, miss: GRAPH_MISS_COLOR, invalidMiss: GRAPH_INVALID_MISS_COLOR
    };
    return (
        <div className="d-flex justify-content-center">
            <div className={styles.graphStacker+" shadow p-3 mb-5 bg-body rounded"}>
                <GraphHolder graphDrawer={graphDrawer} pointsDrawer={pointsDrawer} graphParams={graphParams} graphStyle={graphStyle}/>
                <ClickableGraph graphParams={graphParams} graphStyle={graphStyle}/>
            </div>
        </div>
    )
}