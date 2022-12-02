import {useAppDispatch, useAppSelector} from "../../hooks";
import {ActionCreatorWithOptionalPayload} from "@reduxjs/toolkit";
import React, {FC, useState} from "react";
import {RootState} from "../../store";
import {getValue} from "@testing-library/user-event/dist/utils";
import {ChooserParams} from "./ChooserWrapper";
import {Form, Row} from "react-bootstrap";
import * as styles from './preventValidation.module.css'

export interface CheckboxChooserParams extends ChooserParams{
    options: Array<number>
}
export const CheckboxChooser: FC<CheckboxChooserParams> = ({getValue, setValue, options}) => {
    const dispatch = useAppDispatch();
    // const selected = useAppSelector(state => getValue(state));
    const [localValue, setLocal] = useState('');

    const checkboxChosen = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.checked) {
            setLocal(e.target.value);
            dispatch(setValue(Number(e.target.value)))
        }
    }
    return (
        <div>
            <Form.Control type="text" defaultValue={localValue} isValid={localValue!=''} required className="d-none"/>
            {options.map(option => <OneCheckbox option={option} checkboxChosen={checkboxChosen} getValue={getValue} key={option}/>)}
            <Form.Control.Feedback type="invalid">
                This value is required
            </Form.Control.Feedback>
        </div>
    )
}

interface OneCheckboxParam {
    option: number,
    checkboxChosen: (e: React.ChangeEvent<HTMLInputElement>) => void,
    getValue: (state: RootState) => number,
    key: number
}

const OneCheckbox: FC<OneCheckboxParam> = ({option, checkboxChosen, getValue}) => {
    const selected = useAppSelector(state => getValue(state))
    return(
        <span>
            <Form.Check inline={true} type="checkbox" label={option} value={option} onChange={checkboxChosen} checked={selected==option}
            required={selected==undefined}/>
        </span>
    )
}