import {FC} from "react";
import {Button, ButtonToolbar} from "react-bootstrap";
import {PaginationMenuItem} from "./PaginationMenuItem";
import {DropdownMenuItem} from "./DropdownMenuItem";

export interface TableHeaderProps {
    itemsCount: bigint,
    itemsPerPage: number,
    setItemsPerPage: (itemsPerPage: number) => void,
    currentPage: number,
    setCurrentPage: (currentPage: number) => void,
    clearPost: () => void
}

export const TableHeader: FC<TableHeaderProps> = ({itemsCount, itemsPerPage, setItemsPerPage, currentPage, setCurrentPage, clearPost}) => {
    return (
        <ButtonToolbar>
            <Button onClick={() => clearPost()}>Clear</Button>
            <PaginationMenuItem itemsCount={itemsCount} itemsPerPage={itemsPerPage} currentPage={currentPage} setCurrentPage={setCurrentPage}/>
            <DropdownMenuItem itemsPerPage={itemsPerPage} setItemsPerPage={setItemsPerPage}/>
        </ButtonToolbar>
    )
}