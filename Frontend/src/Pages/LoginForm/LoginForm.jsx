import React, { useState, useRef, useEffect } from "react";
import {
  MDBContainer,
  MDBTabs,
  MDBTabsItem,
  MDBTabsLink,
  MDBTabsContent,
  MDBTabsPane,
  MDBBtn,
  MDBIcon,
  MDBInput,
  MDBCheckbox,
  MDBValidation,
} from "mdb-react-ui-kit";
import { checking } from "../../Components/Utils";
import { useAtom } from "jotai";
import state from "../../Components/AtomStates";

function LoginForm() {
  const [user, setUser] = useAtom(state.user);
  const [isLoggedIn, setisLoggedIn] = useAtom(state.isLoggedIn);
  const [modalShow, setModalShow] = useAtom(state.modalShow);
  const [justifyActive, setJustifyActive] = useState(
    isLoggedIn ? "tab1" : "tab2"
  );
  const [formValue, setFormValue] = useState({
    email: "valentin@gmail.com",
    password: "parolaparola",
    firstName: "Valentin",
    lastName: "Vali",
  });
  const { email, password, firstName, lastName } = formValue;
  let restaurant = null;

  const emailRef = useRef(null);
  const firstNameRef = useRef(null);
  const lastNameRef = useRef(null);
  const passwordRef = useRef(null);

  const emailLoginRef = useRef(null);
  const passwordLoginRef = useRef(null);

  const handleSignInSubmit = () => {
    if (
      emailRef.current.checkValidity() &&
      firstNameRef.current.checkValidity() &&
      lastNameRef.current.checkValidity() &&
      passwordRef.current.checkValidity()
    ) {
      fetch("http://localhost:8080/api/v1/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formValue),
      })
        .then((res) => res.json())
        .then((data) => {
          localStorage.setItem("token", data.token);
          const storedValue = localStorage.getItem("token");
          console.log("Stored value: ", storedValue);
          alert("Account created!");

          checking.autoLogInWithToken({ setModalShow, setUser, setisLoggedIn });
        })
        .catch((err) => {
          console.error(err);
          checking.wrongInput(emailRef.current);
          alert("Email addres allready registered! Please try again!");
        });
    }
  };

  // const handleLogInSubmit = () => {
  //   fetch(
  //     `http://localhost:8080/account/getUserByEmail/${emailRef.current.value}`
  //   )
  //     .then((res) => res.json())
  //     .then((data) => {
  //       setUser(data);
  //       if (password === data.password) {
  //         setisLoggedIn(true);
  //         setModalShow(false);
  //       } else {
  //         alert("Incorect password");
  //         checking.wrongPass(passwordLoginRef.current);
  //       }
  //     })
  //     .catch((err) => {
  //       alert("User does not exist");
  //       console.error("User not exist " + err);
  //       checking.userNotExist(emailLoginRef.current);
  //     });
  // };
  const handleLogInSubmit = () => {
    fetch("http://localhost:8080/api/v1/auth/authenticate", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    })
      .then((res) => res.json())
      .then((data) => {
        if (data.token == "Account  doesn't exists") {
          checking.wrongInput(emailLoginRef.current);
          alert("Account  doesn't exists");
        } else {
          localStorage.setItem("token", data.token);
          alert("Account Loggedin!");
          checking.autoLogInWithToken({ setModalShow, setUser, setisLoggedIn });
        }
      })
      .catch((err) => {
        checking.wrongInput(passwordLoginRef.current);
        alert("Incorect Password");
        console.error(err);
      });
  };

  const onChangeInput = (e) => {
    const { name, value } = e.target;
    setFormValue({ ...formValue, [name]: value });
  };

  const handleJustifyClick = (value) => {
    if (value === justifyActive) {
      return;
    }
    setJustifyActive(value);
    setisLoggedIn((prev) => !prev);
  };

  return (
    <MDBContainer
      className="p-3 d-flex flex-column "
      style={{ backgroundColor: "none ", width: "80%" }}
    >
      <MDBTabs
        pills
        justify
        className="mb-3 d-flex flex-row justify-content-between"
      >
        <MDBTabsItem>
          <MDBTabsLink
            onClick={() => handleJustifyClick("tab1")}
            active={justifyActive === "tab1"}
          >
            Login
          </MDBTabsLink>
        </MDBTabsItem>
        <MDBTabsItem>
          <MDBTabsLink
            onClick={() => handleJustifyClick("tab2")}
            active={justifyActive === "tab2"}
          >
            Register
          </MDBTabsLink>
        </MDBTabsItem>
      </MDBTabs>

      <MDBTabsContent>
        <MDBTabsPane show={justifyActive === "tab1"}>
          <MDBValidation onSubmit={handleLogInSubmit}>
            <div className="text-center mb-3">
              <p>Sign in with:</p>

              <div
                className="d-flex justify-content-between mx-auto"
                style={{ width: "40%" }}
              >
                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-1"
                  style={{ color: "#ffd700" }}
                >
                  <MDBIcon fab icon="facebook-f" size="sm" />
                </MDBBtn>

                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-1"
                  style={{ color: "#ffd700" }}
                >
                  <MDBIcon fab icon="twitter" size="sm" />
                </MDBBtn>

                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-1"
                  style={{ color: "#ffd700" }}
                >
                  <MDBIcon fab icon="google" size="sm" />
                </MDBBtn>

                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-1"
                  style={{ color: "#ffd700" }}
                >
                  <MDBIcon fab icon="github" size="sm" />
                </MDBBtn>
              </div>

              <p className="text-center mt-3">or:</p>
            </div>

            <MDBInput
              wrapperClass="mb-4"
              label="Email address"
              labelClass="text-white"
              id="form1"
              type="email"
              value={email}
              name="email"
              ref={emailLoginRef}
              onChange={onChangeInput}
              required
              onInput={checking.email}
              style={{ color: "white" }}
              // validation = "Plsease provide an email adress"
            />
            <MDBInput
              wrapperClass="mb-4"
              label="Password"
              labelClass="text-white"
              id="form2"
              type="password"
              value={password}
              name="password"
              ref={passwordLoginRef}
              onChange={(e) => {
                onChangeInput(e);
                checking.password(e);
              }}
              required
              style={{ color: "white" }}
              // onInput={checking.password}
            />

            <div className="d-flex justify-content-between mx-4 mb-4">
              <MDBCheckbox
                name="flexCheck"
                value=""
                id="flexCheckDefault"
                label="Remember me"
              />
              <a href="!#">Forgot password?</a>
            </div>

            <MDBBtn
              className="mb-4 w-100 bg-gradient-to-r from-yellow-400 via-yellow-500 to-yellow-600"
              type="submit"
            >
              Sign in
            </MDBBtn>
            <p className="text-center">
              Not a member? <a href="#!">Register</a>
            </p>
          </MDBValidation>
        </MDBTabsPane>

        <MDBTabsPane show={justifyActive === "tab2"}>
          <MDBValidation onSubmit={handleSignInSubmit}>
            <div className="text-center mb-3">
              <p>Sign up with:</p>

              <div
                className="d-flex justify-content-between mx-auto"
                style={{ width: "40%" }}
              >
                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-1 p-2 bg-gradient-to-r rounded-full  h-10 w-10"
                  style={{ color: "#ffd700" }}
                >
                  <MDBIcon fab icon="facebook-f" size="sm" />
                </MDBBtn>

                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-1 p-2  rounded-full  h-10 w-10"
                  style={{ color: "#ffd700" }}
                >
                  <MDBIcon fab icon="twitter" size="sm" />
                </MDBBtn>

                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-1 p-2 rounded-full h-10 w-10"
                  style={{ color: "#ffd700" }}
                >
                  <MDBIcon fab icon="google" size="sm" />
                </MDBBtn>

                <MDBBtn
                  tag="a"
                  color="none"
                  className="m-1 p-2 rounded-full h-10 w-10"
                  style={{ color: "#ffd700" }}
                >
                  <MDBIcon fab icon="github" size="sm" />
                </MDBBtn>
              </div>

              <p className="text-center mt-3">or:</p>
            </div>

            <MDBInput
              wrapperClass="mb-4"
              label="First Name"
              labelClass="text-white"
              id="form1"
              type="text"
              value={firstName}
              name="firstName"
              ref={firstNameRef}
              onChange={onChangeInput}
              onInput={checking.name}
              required
              style={{ color: "white" }}
              // validation = "Plsease provide an "
            />
            <MDBInput
              wrapperClass="mb-4"
              label="Last Name"
              labelClass="text-white"
              id="form1"
              type="text"
              value={lastName}
              name="lastName"
              ref={lastNameRef}
              onChange={onChangeInput}
              onInput={checking.name}
              required
              style={{ color: "white" }}
              // validation = "Plsease provide an "
            />
            <MDBInput
              wrapperClass="mb-4"
              label="Email"
              labelClass="text-white"
              id="form1"
              type="email"
              value={email}
              ref={emailRef}
              name="email"
              onChange={onChangeInput}
              onInput={checking.email}
              required
              style={{ color: "white" }}
              // validation = "Plsease provide an "
            />
            <MDBInput
              wrapperClass="mb-4"
              label="Password"
              labelClass="text-white"
              id="form1"
              type="password"
              value={password}
              name="password"
              ref={passwordRef}
              onChange={onChangeInput}
              onInput={checking.password}
              required
              style={{ color: "white" }}
              // validation = "Plsease provide an "
            />

            <div className="d-flex justify-content-center mb-4">
              <MDBCheckbox
                name="flexCheck"
                id="flexCheckDefault"
                label="I have read and agree to the terms"
              />
            </div>

            <MDBBtn className="mb-4 w-100 bg-gradient-to-r from-yellow-400 via-yellow-500 to-yellow-600 hover:bg-gradient-to-br">
              Sign up
            </MDBBtn>
          </MDBValidation>
        </MDBTabsPane>
      </MDBTabsContent>
    </MDBContainer>
  );
}

export default LoginForm;
