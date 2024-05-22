// DataTable.js
import React, { useEffect, useState } from 'react';
import './DataTable.css';

function DataTable() {
  const [data, setData] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/api/os') // Endpoint da sua API Spring Boot que retorna os dados
      .then(response => response.json())
      .then(data => setData(data))
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  return (
    <div className="data-table">
      <table>
        <thead>
          <tr>
            <th>Número Sequência</th>
            <th>Nome Cliente</th>
            <th>CPF Cliente</th>
            <th>Marca</th>
            <th>Modelo</th>
            <th>Situação</th>
          </tr>
        </thead>
        <tbody>
          {data.map(row => (
            <tr key={row.nr_sequencia}>
              <td>{row.nr_sequencia}</td>
              <td>{row.nm_cliente}</td>
              <td>{row.cpf_cliente}</td>
              <td>{row.nm_marca}</td>
              <td>{row.nm_modelo}</td>
              <td>{row.ds_situacao}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default DataTable;
