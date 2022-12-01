import React, {FC} from "react";
import {RootState} from "../../store";
import {ActionCreatorWithOptionalPayload} from "@reduxjs/toolkit";
import {Col, Form, Row} from "react-bootstrap";

export interface ChooserWrapperProps {
    chooserName: string,
    children: React.ReactNode
}

export interface ChooserParams {
    getValue: (state: RootState) => number,
    setValue: ActionCreatorWithOptionalPayload<number, any>
}

export const ChooserWrapper: FC<ChooserWrapperProps> = ({chooserName, children}) => {
    return (
        <Form.Group as={Row} className="mb-3">
            <Form.Label column sm={2}>{chooserName}</Form.Label>
            <Col sm={10}>
                {children}
            </Col>
        </Form.Group>
    )
}