import React, { useEffect, useState } from "react";

const App = () => {
  const [parents, setParents] = useState([]);
  const [children, setChildren] = useState([]);
  const [showChildren, setShowChildren] = useState(false);
  const [selectedParentId, setSelectedParentId] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(2);
  const [sortOrder, setSortOrder] = useState("parentId_asc");
  useEffect(() => {
    // Fetch parent transactions from REST API
    fetchParentTransactions();
  }, [currentPage, pageSize, sortOrder]);

  const fetchParentTransactions = async () => {
    try {
      console.log(
        `http://localhost:8080/api/parents?page=${currentPage}&pageSize=${pageSize}&sortBy=${sortOrder}`
      );
      const response = await fetch(
        `http://localhost:8080/api/parents?page=${currentPage}&pageSize=${pageSize}&sortBy=${sortOrder}`
      );
      if (response.status == 200) {
        const data = await response.json();
        setParents(data);
      }
    } catch (error) {
      console.error("Error fetching parent transactions:", error);
    }
  };

  const fetchChildrenData = async (parentId) => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/parents/${parentId}/children`
      );
      const data = await response.json();
      setChildren(data);
    } catch (error) {
      console.error("Error fetching children data:", error);
    }
  };

  const handleTotalPaidAmountClick = (parentId) => {
    setSelectedParentId(parentId);
    fetchChildrenData(parentId);
    setShowChildren(true);
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const handlePageSizeChange = (event) => {
    const newSize = Number(event.target.value);
    setPageSize(newSize);
    setCurrentPage(1);
  };

  const handleSortOrderChange = () => {
    const newSortOrder =
      sortOrder === "parentId_asc" ? "parentId_desc" : "parentId_asc";
    setSortOrder(newSortOrder);
    sortParentTransactions(newSortOrder);
  };

  const sortParentTransactions = (order) => {
    const sortedParents = [...parents];
    sortedParents.sort((a, b) => {
      if (order === "asc") {
        return a.id - b.id;
      } else {
        return b.id - a.id;
      }
    });
    setParents(sortedParents);
  };

  return (
    <div>
      <h2>Parent Transactions</h2>
      <div className="pagination">
        <button
          onClick={() => handlePageChange(currentPage - 1)}
          disabled={currentPage === 1}
        >
          Previous
        </button>
        <span>Page number: {currentPage}</span>
        <button onClick={() => handlePageChange(currentPage + 1)}>Next</button>
      </div>
      <div className="pageSize">
        <label>Page Size:</label>
        <select value={pageSize} onChange={handlePageSizeChange}>
          <option value={2}>2</option>
          <option value={5}>5</option>
          <option value={10}>10</option>
        </select>
      </div>
      <table>
        <thead>
          <tr>
            <th>Parent ID</th>
            <th>Sender</th>
            <th>Receiver</th>
            <th>
              Total Amount{" "} - (Sort by parent Id{" "}
              <button onClick={handleSortOrderChange}>
                {sortOrder === "parentId_asc" ? "▲" : "▼"}
              </button>
              )
            </th>
            <th>Total Paid Amount</th>
          </tr>
        </thead>
        <tbody>
          {parents.map((parent) => (
            <tr key={parent.id}>
              <td>{parent.id}</td>
              <td>{parent.sender}</td>
              <td>{parent.receiver}</td>
              <td>{parent.totalAmount}</td>
              <td>
                <button onClick={() => handleTotalPaidAmountClick(parent.id)}>
                  {parent.totalPaidAmount}
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {showChildren && (
        <div>
          <h2>Children Data</h2>
          <table>
            <thead>
              <tr>
                <th>Child ID</th>
                <th>Sender</th>
                <th>Receiver</th>
                <th>Total Amount</th>
                <th>Paid Amount</th>
              </tr>
            </thead>
            <tbody>
              {children.map((child) => (
                <tr key={child.id}>
                  <td>{child.id}</td>
                  <td>{child.sender}</td>
                  <td>{child.receiver}</td>
                  <td>{child.totalAmount}</td>
                  <td>{child.paidAmount}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default App;
