import React, {FC, useState} from "react";
import {Button, ButtonToolbar, Container, Pagination, Row} from "react-bootstrap";
import {useAttemptsPageQuery, useAttemptsQuery, useClearMutation, useShootMutation} from "../../services/attempts";
import {TableHeader} from "../misc/TableHeader";
import {AttemptsTable} from "./AttemptsTable";
import {TableFooter} from "../misc/TableFooter";
import {setCurrentPage, setItemsPerPage} from "../../slices/paginationSlice";
import {useAppDispatch, useAppSelector} from "../../hooks";

export interface AttemptsControllerProps {
}

export const AttemptsController: FC<AttemptsControllerProps> = ({}) => {
    const dispatch = useAppDispatch();
    const [clearPost, {}] = useClearMutation();
    const currentPage = useAppSelector(state => state.pagination.currentPage);
    const dispatchCurrentPage = (currentPage: number) => dispatch(setCurrentPage(currentPage))
    const itemsPerPage = useAppSelector(state => state.pagination.itemsPerPage);
    const dispatchItemsPerPage = (itemsPerPage: number) => dispatch(setItemsPerPage(itemsPerPage))
    const id = useAppSelector(state => state.auth.user.id)
    const {data: attemptsPage, isLoading} = useAttemptsPageQuery({userId: id, page: currentPage-1, size: itemsPerPage});
    if (isLoading) return (<></>)
    return (
        <Container className="shadow p-3 mb-5 bg-body rounded">
            <Row>
                <TableHeader itemsCount={attemptsPage.totalLength} itemsPerPage={itemsPerPage} setItemsPerPage={dispatchItemsPerPage} currentPage={currentPage} setCurrentPage={dispatchCurrentPage} clearPost={clearPost}/>
            </Row>
            <Row>
                <AttemptsTable attempts={attemptsPage.attempts} isLoading={isLoading}/>
            </Row>
            <Row>
                <TableFooter itemsCount={attemptsPage.totalLength} itemsPerPage={itemsPerPage} setItemsPerPage={dispatchItemsPerPage}
                             showedItems={attemptsPage.attempts.map(attempt => attempt.number)}/>
            </Row>
        </Container>
    )
}