using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GeneticAlgorithm
{
    class FilePrinter:IPrinter
    {
        //----------------------------------------------------
        //Поля
        //----------------------------------------------------
     
            //Файл
        private StreamWriter file;

        //----------------------------------------------------
        //Методы
        //----------------------------------------------------
        /// <summary>
        /// Выводит строку в файл
        /// </summary>
        /// <param name="output">Строка, которую нужно вывести</param>
        public void WriteLine(string output)
        {
            file.WriteLine(output);
        }


        /// <summary>
        /// Закрывает файл
        /// </summary>
        public void Clear()
        {
            file.Close();
        }

        //----------------------------------------------------
        //Конструкторы
        //----------------------------------------------------
        public FilePrinter(string FilePath)
        {
            file = new StreamWriter(FilePath);
        }
    }
}
