import React, {useState, useEffect} from 'react'
import axios from "axios";
import {useNavigate, useLocation, useParams} from "react-router-dom";
import Seat from '../../src/components/Seat';
import clsx from 'clsx'
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import ToggleButton from 'react-bootstrap/ToggleButton';
import {Form, Button} from "react-bootstrap";
import './SeatList.css'

const SeatList = () => {
  const [radioValue, setRadioValue] = useState('1');
    const [data,setData] = useState([]);
    const [offer,setOffer] = useState([]);
    const navigate = useNavigate();

    const myValue = "a"
    const addReservation = async (myValue) => {
        const response = await axios.post(`http://localhost:8080/reservation`,myValue);
        console.log(response);
        alert("Reserved");
        setTimeout(() => { navigate("/") }, 500);
    }
    const radios = [
      { name: 'Find', value: '1' },
      { name: 'Choose', value: '2' },
    ];
   
    const getData = async () => {
        const res = await axios.get("http://localhost:8080/seats/1");
        setData(res.data);
    }

    const getOffer = async () => {
      const res = await axios.get("http://localhost:8080/offer/1");
      setOffer(res.offer);
  }
    useEffect(() => {
        getData();
        
    },[])

  return (
    <div className='container' style={{marginTop: "25px"}}>
      <h1>Seats</h1><br></br>
     
      <div className='contentSeat'>
        Proposition of seat: {offer.seats}
        <div className='seats'>

        {data.filter(seat => seat.number <= 45).map(filteredSeat => (
            <Seat busy={filteredSeat.busy} key={filteredSeat.seatId}>
                {filteredSeat.number}
            </Seat>
        ))}
        </div>
        <div className='hallway'></div>
        <div className='seats2'>
        {data.filter(seat => seat.number> 45).map(filteredSeat => (
            <Seat busy={filteredSeat.busy} key={filteredSeat.seatId}>
                {filteredSeat.number}
            </Seat>
        ))}
        </div>
        </div>
        <ButtonGroup>
        {radios.map((radio, idx) => (
          <ToggleButton
            key={idx}
            id={`radio-${idx}`}
            type="radio"
            variant={idx % 2 ? 'outline-success' : 'outline-danger'}
            name="radio"
            value={radio.value}
            checked={radioValue === radio.value}
            onChange={(e) => setRadioValue(e.currentTarget.value)}
          >
            {radio.name}
          </ToggleButton>
        ))}
      </ButtonGroup>
      <Button variant="primary" type="submit" onClick={()=>addReservation(myValue)}>
            Reservation
        </Button>
    </div>

  )
}



export default SeatList