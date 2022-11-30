import React, {FC} from "react";
import {QuadrantsGraph} from "./graph/QuadrantsGraph";

export const Home: FC<any> = ({props}) => {
    return(<div>
        <QuadrantsGraph/>
    </div>)
}