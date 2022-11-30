import React, {FC} from "react";
import {QuadrantsGraph} from "./graph/QuadrantsGraph";
import {ShooterForm} from "./ShooterForm";

export const Home: FC<any> = ({}) => {
    return(<div>
        <QuadrantsGraph/>
        <ShooterForm/>
    </div>)
}