import {FC} from "react";
import {ButtonToolbar} from "react-bootstrap";
import {DropdownMenuItem} from "./DropdownMenuItem";


export interface TableFooterProps {
    itemsCount: bigint,
    itemsPerPage: number,
    setItemsPerPage: (itemsPerPage: number) => void,
    currentPage: number,
    setCurrentPage: (currentPage: number) => void
}

export const TableFooter: FC<TableFooterProps> = ({itemsCount, itemsPerPage, setItemsPerPage, currentPage, setCurrentPage}) => {
    return (
        <ButtonToolbar>
            <div>
                Showing {(currentPage-1)*itemsPerPage + 1} - {currentPage*itemsPerPage} of {itemsCount.toString()}
            </div>
            <DropdownMenuItem itemsPerPage={itemsPerPage} setItemsPerPage={setItemsPerPage}/>
        </ButtonToolbar>
    )
}