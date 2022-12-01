import React, {FC} from "react";
import {QuadrantsGraph} from "./graph/QuadrantsGraph";
import {ShooterForm} from "./ShooterForm";
import {AttemptsTable} from "./attempts/AttemptsTable";

export const Home: FC<any> = ({}) => {
    return(<div>
        <QuadrantsGraph/>
        <ShooterForm/>
        <AttemptsTable/>
    </div>)
}