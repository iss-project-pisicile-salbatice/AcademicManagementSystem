import "./App.css";
import LoginForm from "./components/LoginForm";
import { Link } from "react-router-dom";

function App() {
  return (
    <div>
      {" "}
      <LoginForm />
      <Link to="/ProfilePage">Profile Page</Link>
    </div>
  );
}

export default App;
