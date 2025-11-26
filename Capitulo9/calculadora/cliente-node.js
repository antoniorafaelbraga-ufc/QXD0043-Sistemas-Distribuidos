/**
 * Cliente JavaScript/Node.js para consumir o CalculadoraService via SOAP.
 * 
 * Pré-requisitos:
 * - Node.js 14+
 * - npm
 * 
 * Instalação:
 *   npm install soap
 * 
 * Execução:
 *   node cliente-node.js
 */

const soap = require('soap');
const url = 'http://localhost:8080/services/calculadora?wsdl';

async function main() {
    try {
        console.log('===== Cliente SOAP - CalculadoraService =====\n');
        console.log('Conectando ao serviço SOAP...');
        console.log('WSDL:', url, '\n');

        // Criar cliente SOAP a partir do WSDL
        const client = await soap.createClientAsync(url);

        console.log('✓ Conectado ao serviço!\n');

        // Listar operações disponíveis
        console.log('Operações disponíveis:');
        console.log(Object.keys(client.CalculadoraServicePort).join(', '));
        console.log('\n---\n');

        // Chamar método: somar(3, 5)
        console.log('Chamando: somar(3, 5)');
        const [somarResult] = await client.CalculadoraServicePort.somar({ a: 3, b: 5 });
        console.log('Resultado:', somarResult.return, '\n');

        // Chamar método: subtrair(10, 3)
        console.log('Chamando: subtrair(10, 3)');
        const [subtrairResult] = await client.CalculadoraServicePort.subtrair({ a: 10, b: 3 });
        console.log('Resultado:', subtrairResult.return, '\n');

        // Chamar método: multiplicar(4, 7)
        console.log('Chamando: multiplicar(4, 7)');
        const [multiplicarResult] = await client.CalculadoraServicePort.multiplicar({ a: 4, b: 7 });
        console.log('Resultado:', multiplicarResult.return, '\n');

        // Chamar método: dividir(20, 4)
        console.log('Chamando: dividir(20, 4)');
        const [dividirResult] = await client.CalculadoraServicePort.dividir({ a: 20, b: 4 });
        console.log('Resultado:', dividirResult.return, '\n');

        // Testar erro: dividir por zero
        console.log('Chamando: dividir(10, 0) [deve gerar erro]');
        try {
            const [errResult] = await client.CalculadoraServicePort.dividir({ a: 10, b: 0 });
        } catch (error) {
            console.log('✓ Erro capturado (esperado):', error.message, '\n');
        }

        console.log('===== Teste concluído! =====');

    } catch (error) {
        console.error('✗ Erro ao conectar ou chamar serviço:', error.message);
        process.exit(1);
    }
}

main();
