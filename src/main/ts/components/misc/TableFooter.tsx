import {FC} from "react";
import {ButtonToolbar} from "react-bootstrap";
import {DropdownMenuItem} from "./DropdownMenuItem";


export interface TableFooterProps {
    itemsCount: bigint,
    itemsPerPage: number,
    setItemsPerPage: (itemsPerPage: number) => void,
    showedItems: Array<number>
}

export const TableFooter: FC<TableFooterProps> = ({itemsCount, itemsPerPage, setItemsPerPage, showedItems}) => {
    return (
        <div className="d-flex justify-content-between">
                {itemsCount==BigInt(0) ? <div/> :
                    <div>
                        Showing {showedItems.at(0)+1} - {showedItems.at(showedItems.length-1)+1} of {itemsCount.toString()}
                    </div>
                }
            <DropdownMenuItem itemsPerPage={itemsPerPage} setItemsPerPage={setItemsPerPage}/>
        </div>
    )
}