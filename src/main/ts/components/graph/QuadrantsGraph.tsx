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
    GRAPH_HEIGHT,
    GRAPH_OFFSET, GRAPH_POINT_COLOR,
    GRAPH_TEXT_COLOR,
    GRAPH_WIDTH
} from "../../constants/constants";
import {ClickableGraph} from "./ClickableGraph";
import {inspect} from "util";
import * as styles from './graph.module.css';


export class QuadrantsDrawer extends BaseGraphDrawer<QuadrantsInfo> {
    drawData(graph: Graph, style: GraphStyle, data: QuadrantsInfo): void {
        graph.canvas.fillStyle = style.figure;
        for (const quadrant of data.quadrants) {
            quadrant.draw(graph);
        }
    }
}


export const QuadrantsGraph: FC<any> = () => {
    const graphDrawer: GraphDrawer<QuadrantsInfo> = new QuadrantsDrawer();
    const graphParams = createGraphParams(GRAPH_WIDTH, GRAPH_HEIGHT, GRAPH_OFFSET);
    const graphStyle = {background: GRAPH_BACK_COLOR, figure: GRAPH_COLOR, text: GRAPH_TEXT_COLOR, axis: GRAPH_AXIS_COLOR, point: GRAPH_POINT_COLOR};
    return (
        <div className={styles.graphStacker}>
            <GraphHolder graphDrawer={graphDrawer} graphParams={graphParams} graphStyle={graphStyle}/>
            <ClickableGraph graphParams={graphParams} graphStyle={graphStyle}/>
        </div>
    )
}