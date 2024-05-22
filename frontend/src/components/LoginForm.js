import React, { useState } from 'react';
import './LoginForm.css';

function LoginForm() {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
        const response = await fetch('http://localhost:8080/user/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ userName, password }),
        });

        if (response.ok) {
            const data = await response.json();
            const authenticated = data.content;
            if (authenticated) {
                window.location.href = '/os';
            } else {
                setError('Credenciais inválidas.');
            }
        } else {
            setError('Erro na comunicação com o servidor');
        }
    } catch (error) {
        setError('Erro na comunicação com o servidor');
    }
};

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h1>OS CELL</h1>
        <div>
          <input
            type="text"
            placeholder="Usuário"
            value={userName}
            onChange={(e) => setUserName(e.target.value)}
          />
        </div>
        <div>
          <input
            type="password"
            placeholder="Senha"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button type="submit">ENTRAR</button>
        {error && <p className="error">{error}</p>}
      </form>
    </div>
  );
}

export default LoginForm;
