import React, { useState } from 'react';
import './LoginForm.css';

function LoginForm() {
  const [userName, setUserName] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await fetch('/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ userName, password }),
      });

      if (response.ok) {
        // Redirecionar para a página de OS
        window.location.href = '/os';
      } else {
        const errorText = await response.text();
        setError(errorText);
      }
    } catch (error) {
      setError('Erro na comunicação com o servidor');
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h1>OS CELL</h1>
        <input
          type="text"
          placeholder="Usuário"
          value={userName}
          onChange={(e) => setUserName(e.target.value)}
        />
        <input
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">ENTRAR</button>
        {error && <p className="error">{error}</p>}
      </form>
    </div>
  );
}

export default LoginForm;
