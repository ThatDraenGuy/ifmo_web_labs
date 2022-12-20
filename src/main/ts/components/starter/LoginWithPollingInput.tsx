import React, {FC, ReactNode, useEffect, useState} from "react";
import {Form, InputGroup, Spinner} from "react-bootstrap";


export interface LoginWithPollingInputProps {
    onChanged: (e: React.ChangeEvent<HTMLInputElement>) => void,
    isValid: boolean,
    isInvalid: boolean,
    value: string,
    validateUsername: (username: string) => boolean,
    initiateCheck: (should: boolean) => void,
    isChecked: boolean,
    feedback: ReactNode
}

export const LoginWithPolling: FC<LoginWithPollingInputProps> = (props) => {
    const [isCheckDone, setIsCheckDone] = useState(true);
    useEffect(() => {
        if (props.isValid) setIsCheckDone(false);
        else setIsCheckDone(true);
        const timeOutId = setTimeout(() => {
            props.initiateCheck(props.validateUsername(props.value))
        }, 1000);
        return () => {
            props.initiateCheck(false);
            clearTimeout(timeOutId);
        }
    }, [props.value])

    useEffect(() => {
        if (props.isChecked) setIsCheckDone(true);
    }, [props.isChecked])

    const onChanged = (e: React.ChangeEvent<HTMLInputElement>) => {
        if (! props.validateUsername(e.target.value)) props.initiateCheck(false);
        props.onChanged(e);
    }

    return (
        <InputGroup>
            <Form.Control type="text" placeholder="Enter username" required maxLength={15} onChange={onChanged} isValid={props.isValid && isCheckDone} isInvalid={props.isInvalid} value={props.value}/>
            {isCheckDone ? '' :
                <span className="input-group-text">
                    <Spinner/>
                </span>}
            {props.feedback}
        </InputGroup>
    )
}