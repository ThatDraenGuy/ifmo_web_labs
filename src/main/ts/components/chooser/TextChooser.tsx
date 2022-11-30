import {ChooserParams} from "./ChooserWrapper";
import React, {FC} from "react";
import {useAppDispatch} from "../../hooks";


export interface TextChooserParams extends ChooserParams {
    minValue: number,
    maxValue: number
}

export const TextChooser : FC<TextChooserParams> = ({getValue, setValue, minValue, maxValue}) => {
    const dispatch = useAppDispatch()

    const onInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const input: number = Number(e.target.value);
        //TODO
        if (!isNaN(input) && input >= minValue && input <= maxValue) {
            dispatch(setValue(input));
        }
    }

    return (
        <input type="text" onChange={onInputChange}/>
    )
}