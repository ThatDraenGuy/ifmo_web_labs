import {ChooserParams} from "./ChooserWrapper";
import React, {FC, useState} from "react";
import {useAppDispatch, useAppSelector} from "../../hooks";
import {Form} from "react-bootstrap";
import {Simulate} from "react-dom/test-utils";
import input = Simulate.input;


export interface TextChooserParams extends ChooserParams {
    minValue: number,
    maxValue: number
}

export const TextChooser : FC<TextChooserParams> = ({getValue, setValue, minValue, maxValue}) => {
    const dispatch = useAppDispatch()
    const [localValue, setLocal] = useState('');

    const onInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setLocal(e.target.value);
        //TODO
        if (isValid(e.target.value)) {
            const input: number = Number(e.target.value);
            dispatch(setValue(input));
        }
    }

    const isValid = (value: string) => {
        const input: number = Number(value);
        return value!='' && !isNaN(input) && input >= minValue && input <= maxValue
    }
    const isInvalid = (value: string) => {
        const input: number = Number(value);
        return isNaN(input) || input < minValue || input > maxValue
    }

    return (
        <div>
            <Form.Control type="text" value={localValue} required isValid={isValid(localValue)} isInvalid={isInvalid(localValue)} onChange={onInputChange}/>
            <Form.Control.Feedback type="invalid">
                Value should be a number from {minValue} to {maxValue}
            </Form.Control.Feedback>
        </div>
    )
}