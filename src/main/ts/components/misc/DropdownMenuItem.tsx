import {FC} from "react";
import {Dropdown, DropdownButton} from "react-bootstrap";

export interface DropdownMenuProps {
    itemsPerPage: number,
    setItemsPerPage: (value: number) => void
}

export const DropdownMenuItem: FC<DropdownMenuProps> = ({itemsPerPage, setItemsPerPage}) => {
    return (
        <DropdownButton title={itemsPerPage}>
            {[5, 10, 20].map(num => (<Dropdown.Item key={num} onClick={() => setItemsPerPage(num)} active={itemsPerPage==num}>{num}</Dropdown.Item>))}
        </DropdownButton>
    )
}