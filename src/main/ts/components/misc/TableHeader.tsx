import {FC} from "react";
import {Button, ButtonToolbar} from "react-bootstrap";
import {PaginationMenuItem} from "./PaginationMenuItem";
import {DropdownMenuItem} from "./DropdownMenuItem";
import {useGetCurrentUserQuery} from "../../services/auth";

export interface TableHeaderProps {
    itemsCount: bigint,
    itemsPerPage: number,
    setItemsPerPage: (itemsPerPage: number) => void,
    currentPage: number,
    setCurrentPage: (currentPage: number) => void,
    clearPost: (id: number) => void
}

export const TableHeader: FC<TableHeaderProps> = ({itemsCount, itemsPerPage, setItemsPerPage, currentPage, setCurrentPage, clearPost}) => {
    const {currentData: user} = useGetCurrentUserQuery();
    return (
        <>
            <div className="justify-content-between d-sm-flex d-md-none">
                <Button className="h-auto d-inline-block" onClick={() => clearPost(user.id)}>Clear</Button>
                <DropdownMenuItem itemsPerPage={itemsPerPage} setItemsPerPage={setItemsPerPage}/>
            </div>
            <div className="d-flex justify-content-center p-1 justify-content-md-between p-md-0">
                <div className="d-none d-md-block">
                    <Button className="h-auto d-inline-block" onClick={() => clearPost(user.id)}>Clear</Button>
                </div>
                <PaginationMenuItem itemsCount={itemsCount} itemsPerPage={itemsPerPage} currentPage={currentPage} setCurrentPage={setCurrentPage}/>
                <div className="d-none d-md-block">
                    <DropdownMenuItem itemsPerPage={itemsPerPage} setItemsPerPage={setItemsPerPage}/>
                </div>
            </div>
        </>
    )
}