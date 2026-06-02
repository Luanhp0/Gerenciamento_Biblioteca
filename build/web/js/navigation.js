/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const cadastrar = document.getElementById('btn-novocadastro');
cadastrar.addEventListener('click', () => {
    window.location.href = 'novocadastro.jsp';
});

const home = document.getElementById('homebtn');
home.addEventListener('click', () => {
    window.location.href = 'index.html';
});

const cliente = document.getElementById('btn-novocliente');
cliente.addEventListener('click', () => {
    window.location.href = 'novocliente.jsp';
});

const listar = document.getElementById('btn-listar');
listar.addEventListener('click', () => {
    window.location.href = 'resultadoconsultartodos.jsp';
});

document.querySelectorAll('.btn-atualizar').forEach(button => {
    button.addEventListener('click', function() {
        const livroId = this.dataset.id;
        window.location.href = `LivroController?txtid=${livroId}&opcao=ConsultarById`;
    });
});
 document.querySelector('select[name="txttipo"]').addEventListener('change', function() {
        document.getElementById('camposFisico').style.display = this.value === 'FISICO' ? 'block' : 'none';
        document.getElementById('camposDigital').style.display = this.value === 'DIGITAL' ? 'block' : 'none';
});