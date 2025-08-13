import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';

//gse
// Function to search student by ID (outside App)
async function searchStudent(studentId, setStudent, setError) {
  try {
    setError("");
    setStudent(null);

    const response = await fetch(`https://sis-backend.onrender.com/students/${studentId.trim()}`);
    if (!response.ok) {
      throw new Error("Student not found");
    }

    const data = await response.json();
    setStudent(data);
  } catch (err) {
    setError(err.message);
  }
}

// Component to display student details (outside App)
function StudentDetails({ student }) {
  if (!student) return null;
  return (
    <div>
      <h3>Student Details</h3>
      <p><strong>ID:</strong> {student.idNumber}</p>
      <p><strong>Name:</strong> {student.name}</p>
      <p><strong>Age:</strong> {student.age}</p>
      <p><strong>Course:</strong> {student.course}</p>
    </div>
  );
}



// Search input component (outside App)
function StudentSearch({ studentId, setStudentId, onSearch }) {
  return (
    <div className='searchBar'>
      <input
        type="text"
        placeholder="Enter Student ID"
        value={studentId}
        onChange={(e) => setStudentId(e.target.value)}
      />
      <button onClick={onSearch} style={{ padding: "10px 20px", fontSize: "16px" }}>
        Search
      </button>
    </div>
  );
}

// Main App component
function App() {
  const [studentId, setStudentId] = useState("");
  const [student, setStudent] = useState(null);
  const [error, setError] = useState("");

  // Pass a callback to StudentSearch to trigger searchStudent function
  const handleSearch = () => {
    searchStudent(studentId, setStudent, setError);
  };

  return (
    <>
      <div className="mainHeader">
        <h1>Student Management System - SMS</h1>
      </div>

      <div className="toolsBar">
        <StudentSearch
          studentId={studentId}
          setStudentId={setStudentId}
          onSearch={handleSearch}
        />
      </div>

      <div className="studentList" style={{ padding: "20px" }}>
        {error && <p style={{ color: "red" }}>{error}</p>}
        <StudentDetails student={student} />
      </div>

      <div className="viewMenu"></div>
    </>
  );
}

export default App;
