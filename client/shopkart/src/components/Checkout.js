import React, {Component} from "react";
import { connect } from "react-redux";
import {putCheckout} from "../actions/products"
import LoginNavbar from "./LoginNavbar"
import axios from "axios"

const initialState = {
    cardNumber: "",
    expiryMonth:"",
    expiryYear:"",
    cvv:"",
    cardHolder:"",
    cardNumberError: "",
    expiryMonthError: "",
    expiryYearError: "",
    cvvError:"",
    cardHolderError:""
};

export class Checkout extends Component {
    constructor(props) {
        super(props);
        this.onPayment = this.onPayment.bind(this);
    }
    state = {
        initialState
    };

    //Will push token of the used to backend(actions)
    componentWillMount() {
        if (!localStorage.getItem("token")) {
          this.props.history.push("/");
        }
      }


    onChange = event => {
        this.setState({ [event.target.name]: event.target.value }); 
    };

    //validation of each input in card details
    validate = () => {
        let cardNumberError = "";
        let expiryMonthError = "";
        let expiryYearError = "";
        let cvvError = "";
        let cardHolderError = "";
        if(this.state.cardNumber) {
            cardNumberError = "Card number required"
        }
        if(!this.state.expiry) {
            expiryMonthError = "Expiry required"
        }
        if(!this.state.expiry) {
            expiryYearError = "Expiry required"
        }
        if(!this.state.cvv) {
            cvvError = "CVV required"
        }
        if(!this.state.cardHolder) {
            cardHolderError = "Card Holder Name required"
        }
        if (cardNumberError || expiryMonthError || expiryYearError || cvvError || cardHolderError ) {
            this.setState({cardHolderError:cardHolderError, expiryMonthError:expiryMonthError, expiryYearError:expiryYearError, cvvError:cvvError, cardHolderError:cardHolderError});

            return false;
        }
        return true;
    };

    //function to handle changes during submit
    handleSubmit = event => {
        event.preventDefault();
        const isValid = this.componentDidCatch.validate();
        if(isValid){
            console.log(this.state)
            this.setState(initialState)
        }
    }

    //funtion containing logic of payment
    onPayment = e => {
        e.preventDefault();
        const isValid = this.validate();
        if(isValid) {
            let cardDetails = {
                cardNumber: this.state.cardNumber,
                expiry: this.state.expiry,
                cvv: this.state.cvv,
                cardHolder: this.state.cardHolder
            };
            this.props.putCheckout(cardDetails);
            this.setState({
                cardNumber:"",
                expiry:"",
                cvv:"",
                cardHolder:""
            })
            alert("Payment successful");
        }
    }

    //BootStrap code for input boxes and submit button
    render() {
        console.log(this.props);
            return(
                    <div>
                        <LoginNavbar />
                    <div
                        className="container"
                        align="center"
                        style={{
                            width: 20 + "em",
                            marginTop: 3 + "em",
                            height: "auto"
                        }}
                    >
                                <h1>Payment Page</h1>
                                <p>
{/* enter card number, expiry month, expiry year, cvv, card holder name to make payment using credit card */}
                                    <input
                                        type="text"
                                        name="cardNumber"
                                        placeholder="Enter Card Number"
                                        maxLength={16}
                                        value={this.state.cardNumber}
                                        onChange= {this.onChange}
                                        required
                                    />
                                </p>
                                <p>
                                    <input
                                        type="text"
                                        name="exipryMonth"
                                        placeholder="Month MM"
                                        maxLength={2}
                                        value={this.state.expiryMonth}
                                        onChange= {this.onChange}
                                        required
                                    
                                    />
                                </p>
                                <p>
                                    <input
                                        type="text"
                                        name="expiryYear"
                                        placeholder="Year YYYY"
                                        maxLength={4}
                                        value={this.state.expiryYear}
                                        onChange= {this.onChange}
                                        required
                                    
                                    />
                                </p>
                                <p>
                                    <input
                                        type="text"
                                        name="cvv"
                                        placeholder="Enter CVV"
                                        maxLength={3}
                                        value={this.state.cvv}
                                        onChange= {this.onChange}
                                        required
                                    
                                    />
                                </p>
                                <p>
                                    <input
                                        type="text"
                                        name="name"
                                        placeholder="Enter Card Holder Name"
                                        value={this.state.cardHolder}
                                        onChange= {this.onChange}
                                        required
                                    
                                    />
                                </p>
                                <p>
                                    <div className="checkouterror" style={{ fontSize: 15, color: "red" }}>
                                    {/* dispatch error from node -vivek */}
                                    {this.props.error ? (
                                        <>
                                        <p>{this.props.error}</p>
                                        </>
                                    ) : (
                                        <p>{this.state.cardNumberError||this.state.expiryonthError||this.state.expiryYearError||this.state.cvvError||this.state.cardHolderError}</p>
                                    )}
                                    </div>
                                </p>

                                <button
                                    onChange={this.handleChange}
                                    onClick={() => {

                                        //sending token to backend route. The token is stored in localStorage
                                        axios.put("http://localhost:8080/cart",
                                            {
                                                token: localStorage.getItem("token")
                                            }

                                        ).then(r => console.log(r.data))

                                        //alert message after success
                                        alert("PAYMENT SUCCESS")
                                    }}
                                    >
                                    Payment
                                </button>
                        </div>
                        </div>
            )
    }
}

const mapStateToProps = state => ({
    products: state.productReducer.products
});

export default connect(
    mapStateToProps,
    { putCheckout}
)(Checkout);