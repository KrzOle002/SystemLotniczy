import React, {useState, useEffect} from 'react'
import axios from "axios";
import Table from 'react-bootstrap/Table';
import "./FlightList.css";
const FlightList = () => {
    const [data,setData] = useState([]);

    const getData = async () => {
        const res = await axios.get("http://localhost:8080/reservations");
        setData(res.data);
    }

    const deleteReservation = async (id) => {
      if(window.confirm("Are you sure?")){
          const response = await axios.delete(`http://localhost:8080/reservation/${id}`);
          if(response.status === 200){
              getData();
          }
      }
  }
 


    useEffect(() => {
        getData();
    },[])

  return (
    <div className='container' style={{marginTop: "25px"}}>
      <h1>Reservations</h1><br></br>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Flight From</th>
            <th>Flight To</th>
            <th>Flight Date</th>
            <th>Seats</th>
            <th>Delete Button</th>
          </tr>
        </thead>
        <tbody>
          {data && data.map((item,key) => {
              return(
                  <tr key={key}>
                      <td>{item.client.name}</td>
                      <td>{item.client.surname}</td>
                      <td>{item.flight.flightFrom}</td>
                      <td>{item.flight.flightTo}  </td>
                      <td>{item.flight.flightDate}</td>
                      <td>{item.place}</td>
                      <td><button className='btn btn-delete' onClick={() => deleteReservation(key)}>Delete</button></td>
                  </tr>
              )
          })}
        </tbody>
      </Table>
    </div>
  )
}

export default FlightList