import React, {FC, useEffect} from "react";
import {ChooserWrapper} from "./chooser/ChooserWrapper";
import {CheckboxChooser} from "./chooser/CheckboxChooser";
import {setR, setY, setX} from "../slices/chooserSlice";
import {TextChooser} from "./chooser/TextChooser";
import {useShootMutation} from "../services/attempts";
import {useAppSelector} from "../hooks";
import {store} from "../store";


export const ShooterForm: FC<any> = () => {
    const [shootPost, {}] = useShootMutation();

    const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        shootPost(store.getState().chooser);
    }

    return (
        <div>
            <form onSubmit={onSubmit}>
                <ChooserWrapper chooserName={"X"}>
                    <CheckboxChooser getValue={state => state.chooser.x} setValue={setX} options={[-4,-3,-2,-1,0,1,2,3,4]}/>
                </ChooserWrapper>
                <ChooserWrapper chooserName={"Y"}>
                    <TextChooser     getValue={state => state.chooser.y} setValue={setY} minValue={-3} maxValue={3} />
                </ChooserWrapper>
                <ChooserWrapper chooserName={"R"}>
                    <CheckboxChooser getValue={state => state.chooser.r} setValue={setR} options={[-4,-3,-2,-1,0,1,2,3,4]}/>
                </ChooserWrapper>
                <input type="submit"/>
            </form>
        </div>
    )
}