import React, {FC, ReactNode, useState} from "react";
import {Form, InputGroup} from "react-bootstrap";

export interface PasswordInputProps {
    onChanged: (e: React.ChangeEvent<HTMLInputElement>) => void,
    isValid: boolean,
    isInvalid: boolean,
    value: string,
    feedback?: ReactNode
}

export const PasswordInput: FC<PasswordInputProps> = ({onChanged, isValid, isInvalid, value, feedback}) => {
    const [isShown, setIsShown] = useState(false);

    const toggleShown = () =>  {
        setIsShown(!isShown);
    }
    return (
        <InputGroup>
            <Form.Control type={isShown ? "text" : "password"} placeholder="Enter password" required maxLength={15}
                          onChange={onChanged} isValid={isValid} isInvalid={isInvalid} value={value}/>
            <span className="input-group-text" onClick={toggleShown}>
                <i className={isShown ? "bi bi-eye" : "bi bi-eye-slash"} aria-hidden="true"/>
            </span>
            {feedback}
        </InputGroup>
    )
}