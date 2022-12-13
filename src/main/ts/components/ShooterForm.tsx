import React, {FC, useEffect, useState} from "react";
import {ChooserWrapper} from "./chooser/ChooserWrapper";
import {CheckboxChooser} from "./chooser/CheckboxChooser";
import {setR, setY, setX} from "../slices/chooserSlice";
import {TextChooser} from "./chooser/TextChooser";
import {useShootMutation} from "../services/attempts";
import {useAppSelector} from "../hooks";
import {store} from "../store";
import {Button, Col, Form, Row} from "react-bootstrap";


export const ShooterForm: FC<any> = () => {
    const [shootPost, {}] = useShootMutation();
    const chooserState = useAppSelector(state => state.chooser);
    const [validated, setValidated] = useState(false);

    const onSubmit = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (e.currentTarget.checkValidity()) {
            shootPost(chooserState);
            setValidated(false);
        } else {
            setValidated(true);
        }
    }

    return (
        <div className="shadow p-3 mb-5 bg-body rounded">
            <Form onSubmit={onSubmit} noValidate validated={validated}>
                <ChooserWrapper chooserName={"X"}>
                    <CheckboxChooser getValue={state => state.chooser.x} setValue={setX} options={[-4,-3,-2,-1,0,1,2,3,4]}/>
                </ChooserWrapper>
                <ChooserWrapper chooserName={"Y"}>
                    <TextChooser     getValue={state => state.chooser.y} setValue={setY} minValue={-3} maxValue={3} />
                </ChooserWrapper>
                <ChooserWrapper chooserName={"R"}>
                    <CheckboxChooser getValue={state => state.chooser.r} setValue={setR} options={[1,2,3,4]}/>
                </ChooserWrapper>
                <Form.Group as={Row} className="mb-3">
                    <Col sm={{ span: 10, offset: 2 }}>
                        <Button type="submit" disabled={!chooserState.x || !chooserState.y || !chooserState.r}>Shoot!</Button>
                    </Col>
                </Form.Group>
            </Form>
        </div>
    )
}