import React, {FC, useState} from "react";
import {Button, ButtonToolbar, Container, Pagination, Row} from "react-bootstrap";
import {useAttemptsPageQuery, useAttemptsQuery, useClearMutation, useShootMutation} from "../../services/attempts";
import {TableHeader} from "../misc/TableHeader";
import {AttemptsTable} from "./AttemptsTable";
import {TableFooter} from "../misc/TableFooter";

export interface AttemptsControllerProps {
}

export const AttemptsController: FC<AttemptsControllerProps> = ({}) => {
    const [clearPost, {}] = useClearMutation();
    const [itemsPerPage, setItemsPerPage] = useState(5);
    const [currentPage, setCurrentPage] = useState(1);
    const {data: attemptsPage, isLoading} = useAttemptsPageQuery({page: currentPage-1, size: itemsPerPage});
    if (isLoading) return (<></>)
    return (
        <Container>
            <Row>
                <TableHeader itemsCount={attemptsPage.totalLength} itemsPerPage={itemsPerPage} setItemsPerPage={setItemsPerPage} currentPage={currentPage} setCurrentPage={setCurrentPage} clearPost={clearPost}/>
            </Row>
            <Row>
                <AttemptsTable attempts={attemptsPage.attempts} isLoading={isLoading}/>
            </Row>
            <Row>
                <TableFooter itemsCount={attemptsPage.totalLength} itemsPerPage={itemsPerPage} setItemsPerPage={setItemsPerPage} currentPage={currentPage} setCurrentPage={setCurrentPage}/>
            </Row>
        </Container>
    )
}