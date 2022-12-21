import {FC, ReactNode} from "react";
import {Pagination} from "react-bootstrap";

export interface PaginationMenuProps {
    itemsCount: bigint,
    itemsPerPage: number,
    currentPage: number,
    setCurrentPage: (page: number) => void;
}

export const PaginationMenuItem: FC<PaginationMenuProps> = ({itemsCount, itemsPerPage, currentPage, setCurrentPage}) => {
    const pageAmount = Math.ceil(Number(itemsCount) / Number(itemsPerPage));

    const isFirst = (page: number) => page==1;
    const isLast = (page: number) => page==pageAmount;

    const isFirstPage = isFirst(currentPage);
    const isLastPage = isLast(currentPage);
    const isShown = pageAmount > 1;

    const changePage = (newPage: number) => {
        if (currentPage==newPage) return;
        setCurrentPage(newPage);
    }
    const onNumberClick = (pageNum: number) => changePage(pageNum);
    const onPrevClick = () => changePage(currentPage - 1);
    const onNextClick = () => changePage(currentPage + 1);
    const onFirstClick = () => changePage(1);
    const onLastClick = () => changePage(pageAmount);
    // const onEllipsisClick = (left: number, right: number) => changePage((left + right) / 2);
    //
    // let isPageOutOfRange: boolean = false;
    // let leftEllipsisNumber: number = 1;
    // const pages = [...new Array(pageAmount)].map((_, i) => {
    //     const pageNum = i+1;
    //     const isNearCurrent = Math.abs(pageNum - currentPage) <= 1;
    //     const isNextNearCurrent = Math.abs(pageNum + 1 - currentPage) <= 1;
    //     const isNextLast = isLast(pageNum+1);
    //
    //     if (isFirst(pageNum) || isLast(pageNum) || isNearCurrent) {
    //         isPageOutOfRange = false;
    //         return <Pagination.Item key={pageNum} active={pageNum==currentPage} onClick={() => onNumberClick(pageNum)}>
    //             {pageNum}
    //         </Pagination.Item>
    //     }
    //     if (!isPageOutOfRange) leftEllipsisNumber = pageNum - 1;
    //
    //     if (!isPageOutOfRange && (isNextNearCurrent || isNextLast)) {
    //         isPageOutOfRange = true;
    //         return <Pagination.Ellipsis key={pageNum} onClick={() => onEllipsisClick(leftEllipsisNumber, pageNum+1)}/>
    //     }
    //     return null;
    // })





    const numberItem = (num: number) => (
        <Pagination.Item key={num} active={num==currentPage} onClick={() => onNumberClick(num)}>
            {num}
        </Pagination.Item>
    )
    const ellipsisItem = (num: number) => (
        <Pagination.Ellipsis key={num} disabled/>
    )

    const getRange = (start: number, end: number) => {
        return Array(end - start + 1)
            .fill(null)
            .map((_, i) => numberItem(start+i))
    }

    const pagination = (currentPage: number, pageCount: number) => {
        let delta: number
        if (pageCount <= 7) {
            // delta == 7: [1 2 3 4 5 6 7]
            delta = 7
        } else {
            // delta == 2: [1 ... 4 5 6 ... 10]
            // delta == 4: [1 2 3 4 5 ... 10]
            delta = currentPage > 4 && currentPage < pageCount - 3 ? 2 : 4
        }

        const range = {
            start: Math.round(currentPage - delta / 2),
            end: Math.round(currentPage + delta / 2)
        }

        if (range.start - 1 == 1 || range.end + 1 == pageCount) {
            range.start += 1
            range.end += 1
        }

        let start = currentPage > delta ? Math.min(range.start, pageCount - delta) : 1;
        let end =   currentPage > delta ? Math.min(range.end, pageCount) : Math.min(pageCount, delta + 1);

        // let pages: Array<ReactNode> =
        //     currentPage > delta
        //         ? getRange(Math.min(range.start, pageCount - delta), Math.min(range.end, pageCount))
        //         : getRange(1, Math.min(pageCount, delta + 1))
        let pages: Array<ReactNode> = getRange(start, end);

        const withDots = (value: ReactNode, pair: Array<ReactNode>) => (pages.length + 1 != pageCount ? pair : [value])

        if (start != 1) {
            pages = withDots(numberItem(1), [numberItem(1), ellipsisItem(2)]).concat(pages)
        }

        if (end < pageCount) {
            pages = pages.concat(withDots(numberItem(pageCount), [ellipsisItem(pageCount-1), numberItem(pageCount)]))
        }

        return pages
    }

    if (isShown) return (
        <Pagination>
            <div className="d-none d-md-flex">
                <Pagination.First onClick={onFirstClick} disabled={isFirstPage}/>
                <Pagination.Prev onClick={onPrevClick} disabled={isFirstPage}/>
            </div>
            {pagination(currentPage, pageAmount)}
            <div className="d-none d-md-flex">
                <Pagination.Next onClick={onNextClick} disabled={isLastPage}/>
                <Pagination.Last onClick={onLastClick} disabled={isLastPage}/>
            </div>
        </Pagination>
    )
    return (<></>)
}