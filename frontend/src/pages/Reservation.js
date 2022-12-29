import React, {useState, useEffect} from 'react';
import {useNavigate, useLocation, useParams} from "react-router-dom";
import axios from 'axios';
import "./Reservation.css";
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import ToggleButton from 'react-bootstrap/ToggleButton';
import {Form, Button} from "react-bootstrap";


import "react-datepicker/dist/react-datepicker.css";

const initialState = {
    name:"",
    surname:"",
    flightFrom:"",
    flightTo:"",
    seats:1,
    flightDate:"",
    nearWindow:true,
    nearHallway:false
}

const Reservation = () => {
    
  const navigate = useNavigate();
  const [radioValue, setRadioValue] = useState('1');

    
  const radios = [
    { name: 'Near Window', value: '1' },
    { name: 'Near Hallway', value: '2' },
    { name: 'No preference', value: '3'}
  ];
    const [state, setState] = useState(initialState);

  const {name,surname,flightFrom,flightTo,seats,flightDate,nearWindow,nearHallway} = state;

  const handleInputChange = (e) => {
        let {name, value} = e.target;
        setState({...state, [name]: value})
  };

  const makeOffer = async (data) => {
    const response = await axios.post(`http://localhost:8080/offer`, data);
    console.log(response);
    
  }

  const handleSubmit = (e) => {
      e.preventDefault();
      if(!name || !surname || !flightDate || !seats || !flightFrom || !flightTo) {
            console.log("It's can't be empty")
      } 
      else{
          makeOffer(state);
          setTimeout(() => { navigate("/seats") }, 500);
      }
  };

  const choose = (e) => {
    setRadioValue(e);
    if(e === "1"){
        setState({...state, nearWindow: true,nearHallway: false});
    }
    else if(e === "2"){
        setState({...state, nearHallway: true , nearWindow: false});
    }
    else{
        setState({...state, nearWindow: false , nearHallway: false});
        
    }
  };


  return (
    <div className='container' style={{marginTop: "25px", marginBottom: "25px"}}>
      <h1>Flight reservation</h1><br></br>
        <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
            <Form.Label>Name: </Form.Label>
            <Form.Control type="text" name="name" id="name" value={name} onChange={handleInputChange}/>
        </Form.Group>
        <Form.Group className="mb-3">
            <Form.Label>Surname: </Form.Label>
            <Form.Control type="text" name="surname" id="surname" value={surname} onChange={handleInputChange}/>
        </Form.Group>
        <Form.Group className="mb-3">
            <Form.Label>Number of seats: </Form.Label>
            <Form.Control type="number" name="seats" id="seats" value={seats} onChange={handleInputChange}/>
        </Form.Group>
        <Form.Group className="mb-3">
            <Form.Label>Flight date: </Form.Label>
            <Form.Control type="date" name="flightDate" id="flightDate" value={flightDate} onChange={handleInputChange}/>
        </Form.Group>
        <Form.Group>
            <Form.Label>Flight from: </Form.Label>
            <Form.Select name="flightFrom" id="flightFrom" value={flightFrom} onChange={handleInputChange}>
                <option>Select start town</option>
                <option value="Bydgoszcz">Bydgoszcz</option>
                <option value="Torun">Toruń</option>
            </Form.Select>
        </Form.Group>
        <Form.Group>
            <Form.Label>Flight to: </Form.Label>
            <Form.Select  name="flightTo" id="flightTo" value={flightTo} onChange={handleInputChange}>
                <option>Select finish town</option>
                <option value="London">London</option>
                <option value="Liverpool">Liverpool</option>
            </Form.Select>
        </Form.Group>
      
        <Button variant="primary" type="submit">
            Find Seats
        </Button>
        </Form>
    </div>
  )
}

export default Reservation