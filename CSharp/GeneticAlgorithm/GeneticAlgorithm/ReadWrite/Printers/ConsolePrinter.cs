using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GeneticAlgorithm
{
    class ConsolePrinter:IPrinter
    {
        //----------------------------------------------------
        //Методы
        //----------------------------------------------------
        /// <summary>
        /// выводит страку на консоль
        /// </summary>
        /// <param name="output">Строка, которую нужно вывести</param>
        public void WriteLine(string output)
        {
            Console.WriteLine(output);
        }
        
        /// <summary>
        /// В данном случае очистка не нужна
        /// </summary>
        public void Clear()
        {

        }
    }
}
