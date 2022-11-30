import {useAppDispatch, useAppSelector} from "../../hooks";
import {ActionCreatorWithOptionalPayload} from "@reduxjs/toolkit";
import React, {FC} from "react";
import {RootState} from "../../store";
import {getValue} from "@testing-library/user-event/dist/utils";
import {ChooserParams} from "./ChooserWrapper";

export interface CheckboxChooserParams extends ChooserParams{
    options: Array<number>
}
export const CheckboxChooser: FC<CheckboxChooserParams> = ({getValue, setValue, options}) => {
    const dispatch = useAppDispatch();

    const checkboxChosen = (e: React.ChangeEvent<HTMLInputElement>) => {
        dispatch(setValue(Number(e.target.value)))
    }
    return (
        <div>
            {options.map(option => <OneCheckbox option={option} checkboxChosen={checkboxChosen} getValue={getValue}/>)}
        </div>
    )
}

interface OneCheckboxParam {
    option: number,
    checkboxChosen: (e: React.ChangeEvent<HTMLInputElement>) => void,
    getValue: (state: RootState) => number
}

const OneCheckbox: FC<OneCheckboxParam> = ({option, checkboxChosen, getValue}) => {
    const selected = useAppSelector(state => getValue(state))
    return(
        <span>
            {option}
            <input type="checkbox" value={option} onInput={checkboxChosen} checked={selected==option}/>
        </span>
    )
}